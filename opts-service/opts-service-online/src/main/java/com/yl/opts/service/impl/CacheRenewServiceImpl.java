package com.yl.opts.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yl.opts.service.AsyncService;
import com.yl.opts.service.CacheRenewService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author: ylyang
 * @date: 2022/5/27
 */
public class CacheRenewServiceImpl implements CacheRenewService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private AsyncService asyncService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final String LOCK_KEY = "lock_cache";
    private static final String CACHE_KEY = "cache_key";



    @Override
    public String getCache() {

        //先去获取数据 如果数据过期则去尝试获取锁 如果获取锁成功 则去异步更新数据 这时候当先线程依然返回旧的数据
        String cacheValue = stringRedisTemplate.opsForValue().get(CACHE_KEY);
        JSONObject jsonObject = JSONObject.parseObject(cacheValue);
        Long guoQi = jsonObject.getLong("guo_qi");
        if (System.currentTimeMillis() <= guoQi){
            //说明还没有过期
            return cacheValue;
        }
        //获取锁
        String value = stringRedisTemplate.opsForValue().get(LOCK_KEY);
        //如果不为空 说明已经有别的线程获取锁了，这时候就直接返回旧的数据即可
        if (StringUtils.isNotBlank(value)){
            asyncService.saveCache();
        }
        //不管有没有获取到去更新缓存的锁，都直接返回旧的数据
        return cacheValue;
    }
}
