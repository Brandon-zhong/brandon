package com.demo.springboot.controller;

import com.demo.springboot.config.ConfigInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author brandon
 * Created by brandon on 2018/11/25.
 */
@RestController
public class InfoController {

//    @Value("${boot.name}")
//    private String name;
//
//    @Value("${boot.location}")
//    private String location;

    @Autowired
    private ConfigInfo configInfo;


    @GetMapping("/info")
    public String info() {

        String name = configInfo.getName();
        String location = configInfo.getLocation();

        return name + " -- " + location;
    }

}
