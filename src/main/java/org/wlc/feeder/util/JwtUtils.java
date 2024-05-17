package org.wlc.feeder.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtils {

    public static final String SECRET_KEY = "akaakking";
    public static final long EXPIRATION_TIME = 60 * 60 * 24 * 1000; // 24 hours in milliseconds

    public static String generateToken(String userId) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + EXPIRATION_TIME);

        String token = Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

        return token;
    }

    public static String validateAndGetOpenId(String token) {
            String openId = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token).getBody().getSubject();

            return openId;
    }
}