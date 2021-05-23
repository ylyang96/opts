package com.yl.opts.mbp;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yl.opts.OnlineOptsServiceApplication;
import com.yl.opts.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: ylyang
 * @date: 2021-04-05
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OnlineOptsServiceApplication.class)
@Slf4j
public class ThreadAsyncTest {


    @Autowired
    private AsyncService asyncService;

    @Test
    public void startTest(){
        while (true){
            asyncService.async();
        }
    }




}
