package com.demo.springbootjsp.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author brandon
 * Created by brandon on 2018/11/25.
 */
@Data
@Component
@ConfigurationProperties(prefix = "boot")
public class ConfigInfo {

    private String name;

    private String location;

}
