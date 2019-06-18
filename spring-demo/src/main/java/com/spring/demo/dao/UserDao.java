package com.spring.demo.dao;

import com.spring.demo.entity.User;

/**
 * @author brandon
 * Created on 2019-06-18.
 * desc:
 */
public class UserDao {

    private String username;
    private Integer age;

    public UserDao(String username, Integer age) {
        this.username = username;
        this.age = age;
    }


    public User getById(int userId) {
        User user = new User();
        user.setId(1001);
        user.setUsername(username);
        user.setAge(age);
        user.setPhone("12345678910");
        user.setEmail("brandon@gmail.com");
        return user;
    }

}
