package com.opts.auth;

import com.yl.opts.auth.OptsAuthApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ylyang
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OptsAuthApplication.class)
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void insertTest001(){
        redisTemplate.opsForHash().put("qt", "123", "哈哈哈");
        stringRedisTemplate.opsForHash().put("qt1", "123", "哈哈哈");
    }
}
