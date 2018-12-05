package com.demo.springbootjsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author brandon
 * Created by brandon on 2018/11/25.
 */
@Controller
public class JspController {

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("msg", "spring boot 集成 jsp .");
        return "index";
    }

}
