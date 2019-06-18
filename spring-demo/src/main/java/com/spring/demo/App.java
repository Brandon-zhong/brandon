package com.spring.demo;

import com.spring.demo.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author brandon
 * Created on 2019-06-18.
 * desc:
 */
public class App {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:app.xml");
        UserService userService = context.getBean("userService", UserService.class);
        String login = userService.login(1001, "123456");
        System.out.println("message --> " + login);

    }

}
