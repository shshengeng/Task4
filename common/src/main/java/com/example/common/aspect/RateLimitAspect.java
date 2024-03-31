package com.example.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class RateLimitAspect {

    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public RateLimitAspect(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Around("@annotation(rateLimit)")
    public Object rateLimit(ProceedingJoinPoint joinPoint, RateLimit rateLimit) throws Throwable {
        // 获取限流配置
        int limit = rateLimit.limit();
        long timeout = rateLimit.timeout();

        // 获取方法名作为Redis中的键
        String methodName = joinPoint.getSignature().toShortString();

        // 从Redis中获取访问次数
        String countStr = redisTemplate.opsForValue().get(methodName);
        int count = (countStr != null) ? Integer.parseInt(countStr) : 0;

        if (count >= limit) {
            throw new RuntimeException("访问频率过高，请稍后再试！");
        }

        // 增加访问次数，并设置超时时间
        redisTemplate.opsForValue().increment(methodName, 1);
        redisTemplate.expire(methodName, timeout, TimeUnit.MILLISECONDS);

        // 执行被限流的方法
        return joinPoint.proceed();
    }
}
