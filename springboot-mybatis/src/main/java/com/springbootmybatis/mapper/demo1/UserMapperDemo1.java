package com.springbootmybatis.mapper.demo1;

import com.springbootmybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapperDemo1 {

    int insert(User record);

    List<User> selectAllUser();
}