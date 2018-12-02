package com.springboomybatis.service.impl;

import com.spirngboot.utils.EncryptUtil;
import com.spirngboot.utils.StringUtil;
import com.springboomybatis.dao.UserMapper;
import com.springboomybatis.entity.User;
import com.springboomybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @author brandon
 * Created on 2018-12-01.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> getAllUser() {

        List<User> users = userMapper.selectAllUser();
        if (null == users || users.size() == 0) {
            return null;
        }

        return users;
    }

    @Override
    public User login(String username, String password) {

        if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)) {
            return null;
        }

        User user = userMapper.selectByUserName(username);
        if (null == user) {
            return null;
        }

        if (!EncryptUtil.encodeSHA256(password).equals(user.getPassword())) {
            return null;
        }
        //验证密码成功后,更新最近登录时间
        user.setLastUse(new Date());
        userMapper.updateByPrimaryKeySelective(user);

        //将密码置空,并返回
        user.setPassword("");

        return user;
    }

    @Override
    public int register(String userName, String password, int age) {

        if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(password)) {
            return 0;
        }

        User user = new User();
        user.setName(userName);
        user.setPassword(EncryptUtil.encodeSHA256(password));
        user.setAge(age);
        user.setLastUse(new Date());

        return userMapper.insert(user);
    }


}
