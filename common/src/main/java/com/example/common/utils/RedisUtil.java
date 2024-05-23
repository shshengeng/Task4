package com.example.common.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    //添加在线用户
    public void addOnlineUser(String key, Object value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    // 获取所有在线用户
    public List<Object> getOnlineUsers(String key) {
        return redisTemplate.opsForList().range(key, 0, -1);
    }

    // 删除在线用户
    public boolean removeOnlineUser(String key, Object value) {
        Long removed = redisTemplate.opsForList().remove(key, 1, value);
        return removed != null && removed > 0;
    }
}
