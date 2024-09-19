package com.dztzb003.j2t.common.utils;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;


/**
 * token工具类
 *
 * @author dztz
 * @date 2024/09/13
 */
public class TokenUtils {
    private static final Long EXPIRATION_TIME = 1000L * 60L * 60L * 3L;

    private static final String SECRET = "DZTZ.J2T";
//    private static final long EXPIRATION_TIME = 864_000_00; // 1 day in milliseconds

    // 生成Token
    public static String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET));
    }

    // 验证Token
    public static DecodedJWT verifyToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET))
                .build();
        return verifier.verify(token);
    }

    // 从Token中获取用户名
    public static String getUsernameFromToken(String token) {
        DecodedJWT decodedJWT = verifyToken(token);
        return decodedJWT.getSubject();
    }
}
