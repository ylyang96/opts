package com.opts.auth.mongo;

import com.yl.opts.auth.OptsAuthApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;


/**
 * @author ylyang
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OptsAuthApplication.class)
public class MongodbTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void testInsert(){
//        LkOnlineCooperateBusinessInfo lkOnlineCooperateBusinessInfo = new LkOnlineCooperateBusinessInfo();
//        lkOnlineCooperateBusinessInfo.setMerchantId(123);
//        lkOnlineCooperateBusinessInfo.setBusinessName("这是一个");
//        lkOnlineCooperateBusinessInfo.setBusinessAddress("观看地址");
//        lkOnlineCooperateBusinessInfo.setContacts("hhh");
//        lkOnlineCooperateBusinessInfo.setSex(1);
//        lkOnlineCooperateBusinessInfo.setBusinessPhone("12312312312");
//        lkOnlineCooperateBusinessInfo.setQrCodeUrl("werawerasdfasdf");
//        lkOnlineCooperateBusinessInfo.setFinishNum(1);
//        lkOnlineCooperateBusinessInfo.setFinishSuccessNum(0);
//        lkOnlineCooperateBusinessInfo.setCreateTime(LocalDateTime.now());
//        lkOnlineCooperateBusinessInfo.setUpdateTime(LocalDateTime.now());
//        mongoTemplate.insert(lkOnlineCooperateBusinessInfo);
        Query query = new Query(Criteria.where("merchantId").is(123));
        Update update = new Update();
        update.set("finishNum", 2);
        update.set("updateTime", LocalDateTime.now());
        mongoTemplate.updateMulti(query, update, LkOnlineCooperateBusinessInfo.class);
    }
}
