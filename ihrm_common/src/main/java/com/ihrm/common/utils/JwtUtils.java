package com.ihrm.common.utils;

import io.jsonwebtoken.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Date;
import java.util.Map;

/**
 * ClassName: JwtUtils
 * Package: com.ihrm.common.utils
 * Description:
 *
 * @Author R
 * @Create 2024/3/26 13:52
 * @Version 1.0
 */
@Getter
@Setter
@ConfigurationProperties("jwt.config")
public class JwtUtils {
    //签名私钥
    private String key;
    //签名失效时间
    private Long ttl;

    /**
     * 设置认证token
     * id登录用户id
     * subject登录用户名
     */
    public String createJwt(String id, String name, Map<String,Object> map) {
        //设置失效时间
        long now = System.currentTimeMillis();
        long exp = now+ttl;
        //创建jwtBuilder
        JwtBuilder jwtbuilder = Jwts.builder().setId(id).setSubject(name)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, key);
        //根据map设置claims
        for (Map.Entry<String,Object> entry :map.entrySet()) {
            jwtbuilder.claim(entry.getKey(),entry.getValue());
        }
        jwtbuilder.setExpiration(new Date(exp));
        //创建token
        String token = jwtbuilder.compact();
        return token;

    }
    /**
     * 解析token字符串
     */
    public Claims parseJwt(String token) {
        Claims body = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
        return body;
    }
}
