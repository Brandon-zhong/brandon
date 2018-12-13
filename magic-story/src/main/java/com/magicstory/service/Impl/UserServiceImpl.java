package com.magicstory.service.Impl;

import com.magicstory.dao.UserDao;
import com.magicstory.entity.User;
import com.magicstory.service.UserService;
import com.spirngboot.utils.*;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author brandon
 * create on 2018-12-13
 * desc: 用户相关逻辑处理类
 */
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserDao userDao;


    /**
     * 获取所有的用户信息
     *
     * @return 返回所有的用户的list形式
     */

    @Override
    public List<User> getAllUser() {

        return null;
    }


    /**
     * 用户登录逻辑处理
     *
     * @param username 用户登录的用户名
     * @param password 用户登录的密码
     * @return 登录成功, 则返回该用户对象, 登录失败则返回null
     */

    @Override
    public User login(String username, String password) {

        return null;
    }


    /**
     * 注册用户操作
     *
     * @param name     注册的用户名
     * @param password 注册的用户密码
     * @param age      注册的用户年龄
     * @return 返回注册操作影响的行数
     */

    @Override
    public int register(String name, String password, Integer age) {


        return 0;
    }


    /**
     * 修改用户信息操作
     *
     * @param user 要修改的用户信息(能修改的用户信息有:密码,年龄)
     * @return 返回影响的航行数
     */

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int modifyUser(User user) {

        return 0;
    }
}
