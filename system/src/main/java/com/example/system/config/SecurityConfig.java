//package com.example.system.config;
//
//package com.boot.config;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
////@EnableWebSecurity:开启SpringSecurity 之后会默认注册大量的过滤器servlet filter
////过滤器链【责任链模式】SecurityFilterChain
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        //authorizeHttpRequests:针对http请求进行授权配置
//        //login登录页面需要匿名访问
//        //permitAll:具有所有权限 也就可以匿名可以访问
//        //anyRequest:任何请求 所有请求
//        //authenticated:认证【登录】
//        http.authorizeHttpRequests(authorizeHttpRequests->
//                authorizeHttpRequests
//                        //********************************角色****************************************
//                        //.requestMatchers("/admin/api").hasRole("admin") //必须有admin角色才能访问到
//                        //.requestMatchers("/user/api").hasAnyRole("admin","user") // /user/api:admin、user都是可以访问
//                        //********************************权限****************************************
//                        .requestMatchers("/admin/api").hasAuthority("admin:api") //必须有admin:api权限才能访问到
//                        .requestMatchers("/user/api").hasAnyAuthority("admin:api","user:api") //有admin:api、user:api权限能访问到
//                        //********************************匹配模式****************************************
//                        .requestMatchers("/admin/api/?").hasAuthority("admin:api") //必须有admin:api权限才能访问到
//                        .requestMatchers("/user/api/my/*").hasAuthority("admin:api") //必须有admin:api权限才能访问到
//                        .requestMatchers("/admin/api/a/b/**").hasAuthority("admin:api") //必须有admin:api权限才能访问到
//                        .requestMatchers("/app/api").permitAll() //匿名可以访问
//                        .requestMatchers("/login").permitAll()
//                        .anyRequest().authenticated()
//        );
//        //现在我们借助异常处理配置一个未授权页面:【实际上是不合理的 我们应该捕获异常信息 通过异常类型来判断是什么异常】
//        http.exceptionHandling(e->e.accessDeniedPage("/noAuth"));
//        //http:后面可以一直点 但是太多内容之后不美