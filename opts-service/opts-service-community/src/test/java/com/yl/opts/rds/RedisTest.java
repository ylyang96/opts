package com.yl.opts.rds;

import com.yl.OptsServiceCommunityApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OptsServiceCommunityApplication.class)
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;



    @Test
    public void insertTest001(){
        redisTemplate.opsForValue().set("me-key", "hello");
        
    }


}
