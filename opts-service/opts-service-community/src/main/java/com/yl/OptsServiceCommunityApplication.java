package com.yl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ylyang
 */
@SpringBootApplication
@MapperScan({"com.yl.opts.db.*.mapper"})
@EnableDiscoveryClient
public class OptsServiceCommunityApplication {
    public static void main(String[] args) {
        SpringApplication.run(OptsServiceCommunityApplication.class, args);
    }
}
