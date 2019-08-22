package com.ljl.util.cache;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author lvjunlong
 * @date 2019/8/22 下午5:42
 */
@SuppressWarnings("unchecked")
@Service
public class BaseRedisCacheImpl extends AbstractBaseRedis implements CacheClient {
    /**
     * 通过hash存放对象
     *
     * @param prefix 前缀
     * @param key    hashKey
     * @param t      对象
     * @return boolean
     */
    @Override
    public <T> boolean setByHash(String prefix, String key, T t) {
        if (key == null || prefix == null) {
            return false;
        }
        redisTemplate.opsForHash().put(prefix, key, t);
        return true;
    }

    /**
     * 获取
     *
     * @param prefix 前缀
     * @param key    hashKey
     * @return Object
     */
    @Override
    public <T> T getByHash(String prefix, String key) {
        if (key == null || prefix == null) {
            return null;
        }

        return (T)redisTemplate.opsForHash().get(prefix, key);
    }

    /**
     * @param prefix 前缀
     * @param key    hashkey
     * @return boolean
     */
    @Override
    public boolean deleteByHash(String prefix, String key) {
        if (null == prefix || null == key) {
            return false;
        }
        return 1 == redisTemplate.opsForHash().delete(prefix, key);
    }

    /**
     * 设置对象
     *
     * @param prefix  前缀
     * @param key     key
     * @param t       Object
     * @param timeout 失效时间
     * @return boolean
     */
    @Override
    public <T> boolean set(String prefix, String key, T t, Long timeout) {
        if (key == null || prefix == null) {
            return false;
        }
        String realKey = prefix + key;
        redisTemplate.opsForValue().set(realKey, t, timeout, TimeUnit.SECONDS);
        return true;
    }

    /**
     * 设置列表缓存
     *
     * @param prefix 前缀
     * @param key    key
     * @param t      Object
     * @return boolean
     */
    @Override
    public <T> boolean setList(String prefix, String key, List<T> t) {
        if (key == null || prefix == null) {
            return false;
        }
        String realKey = prefix + key;
        redisTemplate.opsForList().leftPushAll(realKey, t);
        return false;
    }

    /**
     * 获取对象
     *
     * @param prefix 前缀
     * @param key    key
     * @return Object
     */
    @Override
    public <T> T get(String prefix, String key) {
        if (key == null || prefix == null) {
            return null;
        }
        String realKey = prefix + key;
        return   (T) redisTemplate.opsForValue().get(realKey);
    }

    /**
     * 获取自增的值
     *
     * @param prefix 前缀
     * @param key    key
     * @return
     */
    @Override
    public Long getLongValue(String prefix, String key) {
        return (Long) redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer<String> serializer=redisTemplate.getStringSerializer();
                byte[] rowkey=serializer.serialize(prefix + key);
                byte[] rowval=connection.get(rowkey);
                try {
                    String val=serializer.deserialize(rowval);
                    if(StringUtils.isBlank(val)){
                        return null;
                    }
                    return Long.parseLong(val);
                } catch (Exception e) {
                    return 0L;
                }
            }
        });
    }

    /**
     * 获取列表缓存
     *
     * @param prefix 前缀
     * @param key    key
     * @return Object
     */
    @Override
    public <T> List<T> getList(String prefix, String key) {
        if (key == null || prefix == null) {
            return null;
        }
        String realKey = prefix + key;
        @SuppressWarnings("rawtypes")
        ListOperations listOps= redisTemplate.opsForList();
        return redisTemplate.opsForList().range(realKey, 0, listOps.size(realKey) - 1);
    }

    /**
     * 删除缓存
     *
     * @param prefix 前缀
     * @param key    key
     */
    @Override
    public void delete(String prefix, String key) {
        if (key == null || prefix == null) {
            return;
        }
        String realKey = prefix + key;
        redisTemplate.delete(realKey);
    }

    /**
     * 不存在时添加
     *
     * @param prefix 前缀
     * @param key    key
     * @return Object
     */
    @Override
    public boolean setIfAbsent(String prefix, String key) {
        if (key == null || prefix == null) {
            return false;
        }
        return redisTemplate.opsForValue().setIfAbsent(prefix, key);
    }
}
