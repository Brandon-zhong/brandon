package com.spirngboot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author brandon
 * Created on 2018-12-09.
 * desc: redis 工具类
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    @PostConstruct
    public void init() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
    }

    /**
     * 向redis中设置键值对
     *
     * @param key   设置在redis中的key
     * @param value key对应的值value
     */
    public void setValue(String key, Object value) {
        getOpsForValue().set(key, value);
    }

    /**
     * 根据key从redis中取处对应的值
     *
     * @param key redis中的key
     * @return 返回对应的值
     */
    public Object getValue(String key) {
        return getOpsForValue().get(key);
    }

    private ValueOperations getOpsForValue() {
        return redisTemplate.opsForValue();
    }

    public void setExpire(Object key, int expire) {
    }

}
