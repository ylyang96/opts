package com.yl.opts.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ylyang
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OptsAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(OptsAuthApplication.class, args);
    }
}
