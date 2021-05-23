package com.yl.opts.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author: ylyang
 * @date: 2021-04-05
 */
@Service
@Slf4j
public class AsyncService {

    @Async
    public void async(){
        while (true){
            log.info(Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
