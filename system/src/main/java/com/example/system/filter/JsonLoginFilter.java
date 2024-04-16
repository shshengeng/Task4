package com.example.system.filter;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JsonLoginFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        //提供登录接口，提交过来的是 json 数据，因此需要从 json 中解析出用户名和密码
        if (request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)) {
            try {
                Map<String, String> map = new ObjectMapper().readValue(request.getInputStream(),
                        new TypeReference<Map<String, String>>() {
                        });
                String userName = map.get(getUsernameParameter());
                String passwd = map.get(getPasswordParameter());
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userName, passwd);
                return this.getAuthenticationManager().authenticate(authenticationToken);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

        return super.attemptAuthentication(request, response);
    }
}