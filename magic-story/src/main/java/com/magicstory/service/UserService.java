package com.magicstory.service;

import com.magicstory.entity.User;

import java.util.List;

/**
 * @author brandon
 * create on 2018-12-13
 * desc:
 */
public interface UserService {
    User login(String name, String password);

    List<User> getAllUser();

    int register(String name, String password, Integer age);

    int modifyUser(User user);
}
