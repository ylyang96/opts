package com.yl.opts;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author ylyang
 */
@EnableAsync
@SpringBootApplication
@MapperScan({"com.yl.opts.db.*.mapper"})
public class OnlineOptsServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OnlineOptsServiceApplication.class, args);
    }
}
