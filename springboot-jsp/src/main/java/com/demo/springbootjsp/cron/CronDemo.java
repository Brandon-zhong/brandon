package com.demo.springbootjsp.cron;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: brandon
 * @Date: 2018-11-30
 * @Description:
 */
@Component
public class CronDemo {

    @Scheduled(cron = "0 0/2 * * * ? *")
    public void doTask(){
        System.out.println("执行了一次计划任务");
    }

}
