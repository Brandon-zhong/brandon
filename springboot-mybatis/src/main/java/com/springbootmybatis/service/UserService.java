package com.springbootmybatis.service;

import com.springbootmybatis.entity.User;
import com.springbootmybatis.mapper.demo1.UserMapperDemo1;
import com.springbootmybatis.mapper.demo2.UserMapperDemo2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author brandon
 * Created on 2018-12-01.
 * desc: 用户管理逻辑实现类
 */
@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapperDemo1 userMapperDemo1;

    @Autowired
    private UserMapperDemo2 userMapperDemo2;


    /**
     * 获取所有的用户信息
     *
     * @return 返回所有的用户的list形式
     */

    public List<User> getAllUserDemo1() {
        return userMapperDemo1.selectAllUser();
    }

    public List<User> getAllUserDemo2() {
        return userMapperDemo2.selectAllUser();
    }


    /**
     * 注册用户操作
     *
     * @param userName 注册的用户名
     * @param age      注册的用户年龄
     * @return 返回注册操作影响的行数
     */

    public int register(String userName, int age) {
        return 1;
    }

}
