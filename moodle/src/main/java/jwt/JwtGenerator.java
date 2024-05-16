/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Base64;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;

public class JwtGenerator {

    private static final String SECRET_KEY = "lulu";
    private static final int EXPIRATION_TIME_MS = 3600000;

    public static String generateJwt(Map<String, Object> claims) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME_MS);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static Claims decodeJWT(String jwt) {
        try {
            byte[] decodedKey = Base64.getEncoder().encode(SECRET_KEY.getBytes());
            return Jwts.parserBuilder()
                    .setSigningKey(decodedKey)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
