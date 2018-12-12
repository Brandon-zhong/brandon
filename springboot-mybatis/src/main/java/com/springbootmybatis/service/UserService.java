package com.springbootmybatis.service;

import com.springbootmybatis.entity.User;

import java.util.List;

/**
 * @author brandon
 * Created on 2018-12-01.
 * desc: 用户业务处理的接口类
 */
public interface UserService {

    List<User> getAllUser();

    User login(String username, String password);

    int register(String userName, String password, int age);

    int modifyUser(User user);

}
