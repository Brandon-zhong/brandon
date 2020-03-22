package com.springbootmybatis.mapper.demo1;

import com.springbootmybatis.entity.UserRegister;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author brandon
 * Created on 2020-01-01.
 * desc:
 **/
public interface UserRegisterMapper {

    int insert(UserRegister register);

    int updateTime(@Param("id") int id, @Param("updateTime") Date updateTime);
}
