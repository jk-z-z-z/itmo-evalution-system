package com.itmo.evaluationsystem.Utils;

import io.jsonwebtoken.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static final String SECRET="aXRtbwoK";
    private static final long EXPIRATION_TIME=3600*1000*12;

    public static String generateJwt(Map<String, Object> claims) {

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .compact();
    }


    public static Claims parseJwt(String token) throws ExpiredJwtException{
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();

    }
}
