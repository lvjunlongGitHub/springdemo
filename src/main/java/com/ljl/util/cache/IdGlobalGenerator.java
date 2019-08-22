package com.ljl.util.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.stereotype.Component;

/**
 * <P>
 *     id生成器
 * </P>
 * @author lvjunlong
 * @date 2019/8/22 下午5:55
 */
@Component
public class IdGlobalGenerator {

    @Autowired
    private RedisTemplate<Object, Object> redisClient;

    private static RedisAtomicLong counter;


    /**
     *
     * 获取ID
     *
     * @return ID
     */
    public  Long getSeqId() {
        Long seqId =null;
        try {
            seqId =getCacheSeqIncr();
        } catch (Exception e) {
        }
        return seqId;
    }

    /**
     *
     * 细化粒度，以单表实体类名为序列前缀
     *
     * @return ID
     */
    public  Long getSeqId(Class<?> clz) {
        Long seqId =null;
        String prefix= clz.getSimpleName().toUpperCase();
        try {
            seqId =getCacheSeqIncr(prefix);
        } catch (Exception e) {
        }
        return seqId;
    }

    private static Long getRandNum() {
        Long incrNum = Math.round(Math.random() * 1000);
        return incrNum;
    }

    private static Long getLocalCacheSeq(){
        Long maxNum = System.currentTimeMillis();
        Long randNum = getRandNum();
        return maxNum * 1000 + randNum;
    }

    /**
     *
     * 获取缓存ID系列值
     *
     * @return long
     */
    private  Long getCacheSeqIncr(){
        counter = new RedisAtomicLong( "CACHE_ID_GEN_ORDER", redisClient.getConnectionFactory(), getLocalCacheSeq());
        Long seqId = counter.incrementAndGet();
        if (1l == seqId) {
            seqId = getLocalCacheSeq();
            counter.set(seqId);
        }
        return seqId;
    }

    /**
     *
     * 细化粒度获取自增ID
     *
     * @param prefix 前缀
     * @return long
     */
    private  Long getCacheSeqIncr(String prefix) {
        counter = new RedisAtomicLong("CACHE_ID_GEN_" + prefix, redisClient.getConnectionFactory());
        long seqId = counter.incrementAndGet();

        if (1l == seqId) {
            seqId = getLocalCacheSeq();
            counter.set(seqId);
        }
        return seqId;
    }
}
