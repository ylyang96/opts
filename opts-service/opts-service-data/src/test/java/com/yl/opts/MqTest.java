package com.yl.opts;

import com.yl.opts.mq.SendMqService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ylyang
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OptsAuthServiceDataApplication.class)
public class MqTest {


    @Autowired
    private SendMqService sendMqService;

    @Test
    public void insertTest001() throws InterruptedException {
        sendMqService.send1();
    }
}
