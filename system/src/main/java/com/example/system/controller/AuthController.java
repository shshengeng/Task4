package com.example.system.controller;

import com.example.common.utils.RedisUtil;
import com.example.common.utils.RsaUtil;
import com.example.system.dto.LoginRequest;
import com.example.system.model.User;
import com.example.system.service.UserService;
import com.example.system.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${rsa.private-key}")
    private String privateKey;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
        // 核查验证码
        if (!checkCaptcha(loginRequest.getCaptcha())) {
            return ResponseEntity.badRequest().body("Invalid captcha");
        }

        // 使用RSA解密密码
        String decryptedPassword = RsaUtil.decryptByPrivateKey(loginRequest.getPassword(), privateKey);

        // 验证用户信息
        User user = userService.getUserByName(loginRequest.getUsername());
        if(!user.getPassword().equals(decryptedPassword)){
            return ResponseEntity.badRequest().body("Incorrect username or password");
        }

        // 创建Token令牌
        Map<String, Object> userClaims = new HashMap<>();
        userClaims.put("id", user.getUserId());
        userClaims.put("username", user.getUserName());
        userClaims.put("role", user.isAdmin());
        String token = jwtTokenUtil.generateToken(userClaims);

        // 检查用户是否已在线，如果是则剔除原Token
        if (redisUtil.hasKey(user.getUserName())) {
            redisUtil.delete(user.getUserName());
        }

        // 保存用户在线状态到Redis
        redisUtil.set(user.getUserName(), token, jwtTokenUtil.getExpirationMillis(token), TimeUnit.MILLISECONDS);

        // 返回用户信息和Token
        return ResponseEntity.ok(Map.of("user", user, "token", token));
    }

    private boolean checkCaptcha(String captcha) {

        // 从 Redis 中获取验证码
        String storedCaptcha = (String) redisUtil.get("captcha");
        if(captcha.equals(storedCaptcha)){
            return true;
        }
        return false;
    }
}

