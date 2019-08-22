package com.ljl.util.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author lvjunlong
 * @date 2019/8/22 下午5:37
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractBaseRedis {

    @Autowired
    protected RedisTemplate redisTemplate;

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
