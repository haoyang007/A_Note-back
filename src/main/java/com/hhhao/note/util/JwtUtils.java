package com.hhhao.note.util;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.hhhao.note.entity.User;

/**
 * JWT管理
 * 
 * @author haoy
 *
 */
@Component
public class JwtUtils {
//	获取jwt加密密钥的值（对称加密算法）
    @Value("${jwt.password}")
    private String password;
//	获取jwt超时时间的值
    @Value("${jwt.expiry}")
    private int expiry;

//	jwt负载（payload）中用户邮箱的key
    public static final String PAYLOAD_EMAIL = "email";

    /**
     * 创建jwt，根据实际情况调整负载中的内容
     * 
     * @param user 登录用户
     * @return 创建好的jwt字符串
     */
    public String generateJwt(User user) {
        Date expiryDate = new Date(System.currentTimeMillis() + expiry);
        return JWT.create().withClaim(PAYLOAD_EMAIL, user.getEmail()).withExpiresAt(expiryDate)
                .sign(Algorithm.HMAC256(password));
    }

    /**
     * 验证jwt，并从负载中取出数据
     * 
     * @param token jwt
     * @return 若验证成功，则返回负载中的内容，否则返回null
     */
    public Map<String, Claim> verifyJwt(String token) {
        if (token == null || token.isEmpty()) {
            return null;
        }
        try {
//			解密
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(password)).build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaims();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
