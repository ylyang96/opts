package com.yl.opts.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ylyang
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.yl.opts.auth.mapper")
public class OptsAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(OptsAuthApplication.class, args);
    }
}
