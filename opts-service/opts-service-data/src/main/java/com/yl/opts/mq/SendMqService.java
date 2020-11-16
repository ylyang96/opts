package com.yl.opts.mq;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ylyang
 */
@Service
public class SendMqService {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public void send1() throws InterruptedException {
//        rocketMQTemplate.asyncSend("topic_data", "hahaha", new SendCallback() {
//            @Override
//            public void onSuccess(SendResult sendResult) {
//                System.out.println("发送消息成功消息id是"+sendResult.getMsgId());
//            }
//
//            @Override
//            public void onException(Throwable throwable) {
//                System.out.println(throwable.getMessage());
//                System.out.println("消息发送失败");
//            }
//        });

        rocketMQTemplate.asyncSend("topic_data", MessageBuilder.withPayload("Hello, World").build(), new SendCallback() {
            @Override
            public void onSuccess(SendResult var1) {
                System.out.printf("async onSucess SendResult=%s %n", var1);
            }

            @Override
            public void onException(Throwable var1) {
                System.out.printf("async onException Throwable=%s %n", var1);
            }

        });
        Thread.sleep(5*1000);
        //rocketMQTemplate.convertAndSend("topic_data", "Hello, World!2222222222");
    }
}
