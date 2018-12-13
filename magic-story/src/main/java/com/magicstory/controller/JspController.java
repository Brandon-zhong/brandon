package com.magicstory.controller;

import com.spirngboot.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author brandon
 * create on 2018-12-13
 * desc: jsp 页面的跳转
 */
@Controller
public class JspController {

    /**
     * 用来做jsp页面的跳转的，将跳转控制从后台转移到前端
     *
     * @param url 要跳转的页面
     * @return 跳转
     */
    @GetMapping("/jump")
    public String jump(String url) {
        if (StringUtil.isEmpty(url)) {
            return "404";
        }
        return url;
    }

}
