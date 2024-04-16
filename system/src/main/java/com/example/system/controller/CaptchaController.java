package com.example.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
public class CaptchaController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/captcha")
    public String generateCaptcha() {
        // 生成随机验证码
        String captcha = generateRandomCaptcha();
        // 保存验证码到 Redis
        saveCaptchaToRedis(captcha);
        // 返回生成的验证码给前端
        return captcha;
    }

    private String generateRandomCaptcha() {
        // 生成随机验证码
        return UUID.randomUUID().toString().substring(0, 6);
    }

    private void saveCaptchaToRedis(String captcha) {
        // 将验证码存储到 Redis，设置过期时间为5分钟
        redisTemplate.opsForValue().set("captcha", captcha, 5, TimeUnit.MINUTES);
    }
}
