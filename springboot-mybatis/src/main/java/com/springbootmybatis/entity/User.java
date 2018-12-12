package com.springbootmybatis.entity;

import com.spirngboot.utils.TimeUtil;
import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private Integer id;

    private String name;

    private String password;

    private Integer age;

    private Integer created = TimeUtil.getTimeSecend();

    private Integer lastUse = TimeUtil.getTimeSecend();

    private static final long serialVersionUID = 1L;


}