package com.spring.demo.service;

import com.spring.demo.entity.User;
import com.spring.demo.dao.UserDao;
import org.springframework.util.StringUtils;

/**
 * @author brandon
 * Created on 2019-06-18.
 * desc:
 */
public class UserService {


    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public String login(int id, String password) {

        if (!StringUtils.hasLength(password)) {
            return "密码不能为空!";
        }

        User user = userDao.getById(id);

        if (user == null) {
            return "系统繁忙，请重试!";
        }
        return user.toString();

    }


}
