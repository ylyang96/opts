package com.yl.opts.common.core.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @author ylyang
 */
public class JwtUtils {


    public static final String SECRET = "SecretKey0123458674567890112314567890123456789";
    /**
     * 过期时间:秒
     */
    public static final int EXPIRE = 60*60*4;



    public static String createToken(Integer userId){
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.SECOND, EXPIRE);
        Date expireDate = nowTime.getTime();
        return JWT.create()
                .withClaim("userId", userId)
                .withSubject("dev")
                .withIssuedAt(new Date())
                .withExpiresAt(expireDate)
                .sign(algorithm);
    }

    /**
     * 验证Token
     * @param token
     * @return
     * @throws Exception
     */
    public static Map<String, Claim> verifyToken(String token){
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);
        }catch (Exception e){
            throw new JWTVerificationException("凭证已过期，请重新登录");
        }
        return jwt.getClaims();
    }

    /**
     * 获取用户id
     * @param token
     * @return
     */
    public static Integer getUserId(String token) {
        Map<String, Claim> map = verifyToken(token);
        return map.get("userId").asInt();
    }




}
