package com.springboomybatis.controller;

import com.spirngboot.common.RestResp;
import com.spirngboot.utils.StringUtil;
import com.springboomybatis.entity.User;
import com.springboomybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created on 2018-12-1.
 *
 * @author brandon
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public RestResp login(@RequestParam("username") String userName,
                          @RequestParam("password") String password) {
        if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(password)) {
            return RestResp.fail("用户名或密码不能为空!");
        }

        User login = userService.login(userName, password);
        if (null == login) {
            return RestResp.fail("用户名或密码错误!");
        }

        return RestResp.success("登录成功", login);
    }

    @GetMapping("/allUser")
    public RestResp allUser() {
        List<User> allUser = userService.getAllUser();
        if (null == allUser || allUser.size() == 0) {
            return RestResp.fail("数据错误!");
        }
        return RestResp.success(allUser);
    }

    @GetMapping("/register")
    public RestResp register(@RequestParam("username") String userName,
                             @RequestParam("password") String password,
                             @RequestParam(value = "age", defaultValue = "0") Integer age) {
        if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(password)) {
            return RestResp.fail("用户名或密码不能为空!");
        }

        int register = userService.register(userName, password, age);
        if (register == 0) {
            return RestResp.fail("注册失败!");
        }
        return RestResp.success();
    }

}
