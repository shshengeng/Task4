package com.example.system.security;

import com.example.system.filter.TokenLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Configuration
@Component
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SpringSecurityConfig {

    @Autowired
    private TokenLoginFilter tokenLoginFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 允许通过GET、POST等请求访问的路径配置
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(HttpMethod.GET, "/**").permitAll() // 允许GET请求访问的路径
                                .requestMatchers(HttpMethod.POST, "/**").permitAll() // 允许POST请求访问的路径
                                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll() // 允许Swagger文档访问
                                .requestMatchers("/static").permitAll() //允许访问静态资源
                                .anyRequest().authenticated() // 其他请求需要身份认证
                )
                // 添加JWT Token校验过滤器
                .addFilterBefore(tokenLoginFilter, UsernamePasswordAuthenticationFilter.class)
                // 关闭CSRF保护
                .csrf().disable();
        return http.build();
    }
}
