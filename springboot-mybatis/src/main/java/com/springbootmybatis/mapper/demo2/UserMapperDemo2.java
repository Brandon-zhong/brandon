package com.springbootmybatis.mapper.demo2;

import com.springbootmybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapperDemo2 {

    int insert(User record);

    List<User> selectAllUser();
}