package com.example.firstsb.lib.auth;


import com.example.firstsb.lib.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT认证过滤器,除了登录接口,其他接口都需要认证
 */

@WebFilter(urlPatterns = "/*") //拦截所有请求
public class JwtAuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 设置跨域相关的HTTP头
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        httpResponse.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, Accept");
        httpResponse.setHeader("Access-Control-Max-Age", "3600");
        //登录接口不需要认证
        if ((httpRequest).getRequestURI().equals("/user/login")) {
            chain.doFilter(request, response);
            return;
        }

        //获取请求头中的token
        String token = (httpRequest).getHeader("Authorization");
        //验证token是否有效
        if (token != null && JwtUtil.validateToken(token) && !JwtUtil.isTokenExpired(token)) {
            chain.doFilter(request, response);
        }else{
            //token无效
            httpResponse.setContentType("application/json;charset=UTF-8");
            Response<Object> responseData = Response.error(-1, "token无效");
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResponse = objectMapper.writeValueAsString(responseData);
            httpResponse.getWriter().write(jsonResponse);
        }

    }

}

