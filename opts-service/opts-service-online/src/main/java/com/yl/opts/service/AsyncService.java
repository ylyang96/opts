package com.yl.opts.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author: ylyang
 * @date: 2021-04-05
 */
@Service
@Slf4j
public class AsyncService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    private static final String LOCK_KEY = "lock_cache";

    private static final String CACHE_KEY = "cache_key";

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

    @Async
    public void saveCache(){
        //更新缓存 只有拿到锁的才能进来 所以最终结束的时候需要把锁给删了
        // TODO 查询数据库得到实时数据 然后将数据更新至redis中
        stringRedisTemplate.opsForValue().set(CACHE_KEY, "");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //尽量延迟一会在删除锁
        stringRedisTemplate.delete(LOCK_KEY);
    }
}
