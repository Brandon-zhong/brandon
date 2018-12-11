package com.springboomybatis.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private Integer id;

    private String name;

    private String password;

    private Integer age = 0;    //用户年龄默认为0

    private Integer created;

    private Integer lastUse;

    private static final long serialVersionUID = 1L;

}