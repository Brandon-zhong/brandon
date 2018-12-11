package com.springboomybatis.controller;

import com.spirngboot.common.RestResp;
import com.spirngboot.utils.StringUtil;
import com.springboomybatis.entity.User;
import com.springboomybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author brandon
 * Created on 2018-12-1.
 * desc: 用户controller操作,主要进行参数的合法性校验
 * 以及调用service逻辑处理方法
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录的controller操作
     *
     * @param user 用户登录的用户信息
     * @return 返回前端的登录结果
     */
    @PostMapping("/login")
    public RestResp login(@RequestBody User user) {
        if (null == user) {
            return RestResp.fail("参数错误!");
        }
        if (StringUtil.isEmpty(user.getName()) || StringUtil.isEmpty(user.getPassword())) {
            return RestResp.fail("用户名或密码不能为空!");
        }

        User login = userService.login(user.getName(), user.getPassword());
        if (null == login) {

            Map<String, Object> map = new HashMap<>(2);
            return RestResp.fail("用户名或密码错误!");
        }

        return RestResp.success("登录成功", login);
    }

    /**
     * 获取所有的用户信息 list集合
     *
     * @return 返回所有用户信息的list集合
     */
    @GetMapping("/allUser")
    public RestResp allUser() {
        List<User> allUser = userService.getAllUser();
        if (null == allUser || allUser.size() == 0) {
            return RestResp.fail("数据错误!");
        }
        return RestResp.success(allUser);
    }

    /**
     * 注册用户的操作
     *
     * @param user 注册用户的用户信息
     * @return 返回前端注册的结果
     */
    @PostMapping("/register")
    public RestResp register(@RequestBody User user) {
        if (null == user) {
            return RestResp.fail("参数错误!");
        }
        if (StringUtil.isEmpty(user.getName()) || StringUtil.isEmpty(user.getPassword())) {
            return RestResp.fail("用户名或密码不能为空!");
        }

        int register = userService.register(user.getName(), user.getPassword(), user.getAge());
        if (register == 0) {
            return RestResp.fail("注册失败!");
        }
        return RestResp.success();
    }

    /**
     * 修改用户信息操作 (用户id或者用户姓名至少一项必传)
     *
     * @param user 要修改的用户信息(可修改的信息有:密码,年龄)
     * @return 返回前端修改信息的结果
     */
    @PutMapping("/user")
    public RestResp modifyUser(@RequestBody User user) {
        if (null == user) {
            return RestResp.fail("参数错误!");
        }
        if (Integer.valueOf(0) == user.getId() && StringUtil.isEmpty(user.getName())) {
            return RestResp.fail("用户id和用户名至少有一项有效");
        }
        int modifyUser = userService.modifyUser(user);
        if (0 == modifyUser) {
            return RestResp.fail("修改用户信息失败!");
        }
        return RestResp.success();
    }

}
