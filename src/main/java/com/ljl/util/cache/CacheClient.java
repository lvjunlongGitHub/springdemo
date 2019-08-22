package com.ljl.util.cache;

import java.util.List;

/**
 * <P>
 *     缓存
 * </P>
 * @author lvjunlong
 * @date 2019/8/22 下午5:38
 */
public interface CacheClient {

    /**
     * 通过hash存放对象
     * @param prefix 前缀
     * @param key hashKey
     * @param t 对象
     * @param <T> T
     * @return boolean
     */
    <T> boolean setByHash(String prefix, String key, T t);

    /**
     * 获取
     * @param prefix 前缀
     * @param key hashKey
     * @param <T> T
     * @return Object
     */
    <T> T getByHash(String prefix, String key);

    /**
     *
     * @param prefix 前缀
     * @param key hashkey
     * @return boolean
     */
    boolean deleteByHash(String prefix, String key);



    /**
     * 设置对象
     * @param prefix 前缀
     * @param key key
     * @param t Object
     * @param timeout 失效时间
     * @param <T> T
     * @return boolean
     */
    <T> boolean set(String prefix, String key, T t, Long timeout);

    /**
     * 设置列表缓存
     * @param prefix 前缀
     * @param key key
     * @param t Object
     * @param <T> T
     * @return boolean
     */
    <T> boolean setList(String prefix, String key, List<T> t);

    /**
     * 获取对象
     * @param prefix 前缀
     * @param key key
     * @param <T> T
     * @return Object
     */
    <T> T get(String prefix, String key);

    /**
     * 获取自增的值
     * @param prefix 前缀
     * @param key key
     * @return
     */
    Long getLongValue(String prefix, String key);

    /**
     * 获取列表缓存
     * @param prefix 前缀
     * @param key key
     * @param <T> T
     * @return Object
     */
    <T> List<T> getList(String prefix, String key);

    /**
     * 删除缓存
     * @param prefix 前缀
     * @param key key
     */
    void delete(String prefix, String key);


    /**
     * 不存在时添加
     * @param prefix 前缀
     * @param key key
     * @return Object
     */
    boolean setIfAbsent(String prefix, String key);
}
