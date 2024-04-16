package com.example.system.filter;


import com.example.system.utils.JwtTokenUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.annotation.WebFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@WebFilter(filterName = "TokenLoginFilter", urlPatterns = "/*")
public class TokenLoginFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
        String token = request.getHeader("Authorization");
        if (StringUtils.isBlank(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        //验证 jwt token 是否有效
        Map claims = jwtTokenUtil.getAllClaimsFromToken(token);
        if (claims != null) {
            //已登录，获取用户信息，进行授权
            List<SimpleGrantedAuthority> authorities = ((List<String>) claims.get("powerlist")).stream()
                    .map(power -> new SimpleGrantedAuthority(power)).collect(Collectors.toList());
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(claims.get("username"), null, authorities);
            //这里只要给 Authentication 设置值后，后面有关验证登录的过滤器，就自动通过了
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(request, response);
            return;
        }
    }
}