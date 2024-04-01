package com.ihrm.demo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jdk.nashorn.internal.parser.Token;
import org.junit.Test;

import java.util.Date;

/**
 * ClassName: CreateJwtTest
 * Package: com.ihrm.demo
 * Description:
 *
 * @Author R
 * @Create 2024/3/26 13:30
 * @Version 1.0
 */
public class CreateJwtTest {
    public static void main(String[] args) {
        JwtBuilder builder = Jwts.builder().setId("888")
                .setSubject("小白")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "ihrm");
        System.out.println(builder.compact());
    }

    @Test
    public void test() {
        String key ="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiLlsI_nmb0iLCJpYXQiOjE3MTE0MzExOTJ9.HBPe3nj_DYZad1dJ_Rb5yJvsPXOqO43MLvE_VGPfxfY";
        Claims ihrm = Jwts.parser().setSigningKey("ihrm").parseClaimsJws(key).getBody();
        System.out.println("id:"+ihrm.getId());
        System.out.println("subject:"+ihrm.getSubject());
        System.out.println("IssuedAt:"+ihrm.getIssuedAt());
    }
}
