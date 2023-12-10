package com.example.firstsb.lib.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
/*
* JJWT
* */
public class JwtUtil {
    private static final String signKey = "LSNXUV";//签名密钥
    private static final Long expire = 43200000L; //有效时间

    /**
     * 生成JWT令牌,payload只有username
     */
    public static String generateToken(String username) {

        return  Jwts.builder()
                .setSubject(username)
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
