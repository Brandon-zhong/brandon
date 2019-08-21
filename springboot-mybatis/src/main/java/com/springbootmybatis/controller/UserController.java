package com.springbootmybatis.controller;

import com.spirngboot.common.RestResp;
import com.spirngboot.utils.StringUtil;
import com.springbootmybatis.entity.User;
import com.springbootmybatis.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * @author brandon
 * Created on 2018-12-1.
 * desc: 用户controller操作,主要进行参数的合法性校验
 * 以及调用service逻辑处理方法
 */
@RestController
@ComponentScan("com.spirngboot.utils")//这里是提示spring扫描这个包
public class UserController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserService userService;


    /**
     * 获取所有的用户信息 list集合
     *
     * @return 返回所有用户信息的list集合
     */
    @GetMapping("/allUser/Demo1")
    public RestResp allUserDemo1() {
        List<User> allUserDemo1 = userService.getAllUserDemo1();
        return RestResp.success(allUserDemo1);
    }

    @GetMapping("/allUser/Demo2")
    public RestResp allUserDemo2() {
        List<User> allUserDemo2 = userService.getAllUserDemo2();
        return RestResp.success(allUserDemo2);
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
        int register = userService.register(user.getName(), user.getAge());
        if (register == 0) {
            return RestResp.fail("注册失败!");
        }
        return RestResp.success();
    }


}
