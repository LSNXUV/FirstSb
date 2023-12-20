package com.example.firstsb.lib.auth;

import com.example.firstsb.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
* JJWT
* */
public class JwtUtil {
    private static final String signKey = "LSNXUV";//签名密钥
    private static final Long expire = 43200000L; //有效时间

    /**
     * 生成JWT令牌,payload只有username
     */
    public static String generateToken(User user) {
        //claims中存储的是JWT第二部分负载 payload 中存储的内容
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());
        claims.put(("name"), user.getName());
        return  Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .signWith(SignatureAlgorithm.HS512, signKey)
                .compact();
    }

    //验证JWT令牌是否合法(不管是否过期)
    public static boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(signKey)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 解析JWT令牌
     *
     * @param token 令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 判断JWT令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public static boolean isTokenExpired(String token) {
        return parseToken(token).getExpiration().before(new Date());
    }

}
