package com.springbootmybatis.service;

import com.springbootmybatis.mapper.demo1.UserRegisterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author brandon
 * Created on 2020-01-01.
 * desc:
 **/
@Service
public class UserRegisterService {
    @Autowired
    private UserRegisterMapper userRegisterMapper;

    @Transactional(propagation = Propagation.NESTED)
    public int updateTime(int id) {
        userRegisterMapper.updateTime(id, new Date());
        return 1;
    }

}
