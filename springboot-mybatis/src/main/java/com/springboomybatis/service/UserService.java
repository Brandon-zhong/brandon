package com.springboomybatis.service;

import com.springboomybatis.entity.User;

import java.util.List;

/**
 * @author brandon
 * Created on 2018-12-01.
 */
public interface UserService {

    List<User> getAllUser();

    User login(String username, String password);

    int register(String userName, String password, int age);
}
