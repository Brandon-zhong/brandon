package com.springboomybatis.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private Integer id;

    private String name;

    private String password;

    private Integer age;

    private Date created;

    private Date lastUse;

    private static final long serialVersionUID = 1L;

}