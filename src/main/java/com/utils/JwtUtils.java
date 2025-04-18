package com.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

public class JwtUtils {
    // 密钥
    private static final String SECRET_KEY = "sheng";
    // 过期时间 12小时/12
    private static final Long EXPIRE = 43200000L * 2 * 10;

    /**
     * 生成JWT令牌
     *
     * @param claims JWT第二部分负载 payload 中存储的内容
     * @return JWT令牌
     */
    public static String generateJwt(Map<String, Object> claims) {
        // 生成JWT令牌
        return Jwts.builder()
                // 设置自定义内容 第二部分负载 payload 中存储的内容
                .addClaims(claims)
                // 设置过加密算法
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                // 设置过期时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                // 得到JWT令牌
                .compact();
    }

    /**
     * 解析JWT令牌
     *
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt) {
        // 解析JWT令牌
        return Jwts.parser()
                // 设置解析用的密钥
                .setSigningKey(SECRET_KEY)
                // 设置要解析的JWT令牌
                .parseClaimsJws(jwt)
                // 得到JWT第二部分负载 payload 中存储的内容
                .getBody();
    }

    /**
     * 从http request中获取token负载
     *
     * @param request http请求
     * @return JWT第二部分负载 payload 中存储的内容
     * @throws RuntimeException JWT解析异常 为空或者解析错误或者过期
     */
    public static Claims getClaimsFromRequest(HttpServletRequest request) {

        try {
            //获取请求的jwt令牌
            String jwt = request.getHeader("token");
            return parseJWT(jwt);
        } catch (Exception e) {
            throw new RuntimeException("JWT parse error");
        }
    }
}
