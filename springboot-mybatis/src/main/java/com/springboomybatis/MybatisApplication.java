package com.springboomybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author brandon
 * Created by brandon on 2018/11/29.
 */
@SpringBootApplication
@EnableTransactionManagement  //开启spring事务支持
public class MybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }

}
