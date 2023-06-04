package com.yaman_springboot.blog.security;

import com.yaman_springboot.blog.exceptions.BlogApiException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.text.DateFormat;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private long jwtExpirationInMs;

    //generate token
    public String generateToken(Authentication authentication){
        try {
            String username = authentication.getName();
            Date currentDate = new Date();
            Date expireDate = new Date(currentDate.getTime() + jwtExpirationInMs); //7 days

            // Static key for HS512 (replace with your own key)
            String base64Key = jwtSecret;
            byte[] decodedKey = Base64.getDecoder().decode(base64Key);
            SecretKey key = new SecretKeySpec(decodedKey, SignatureAlgorithm.HS512.getJcaName());

            return Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(expireDate)
                    .signWith(key,SignatureAlgorithm.HS512)
                    .compact();

        } catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    // get username from the token
    public String getUsernameFromJWT(String token){
        // Static key for HS512 (replace with your own key)
        String base64Key = jwtSecret;
        byte[] decodedKey = Base64.getDecoder().decode(base64Key);
        SecretKey key = new SecretKeySpec(decodedKey, SignatureAlgorithm.HS512.getJcaName());

        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    // validate JWT token
    public boolean validateToken(String token){
        try{
            // Static key for HS512 (replace with your own key)
            String base64Key = jwtSecret;
            byte[] decodedKey = Base64.getDecoder().decode(base64Key);
            SecretKey key = new SecretKeySpec(decodedKey, SignatureAlgorithm.HS512.getJcaName());
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch (SignatureException ex){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "JWT claims string is empty.");
        }
    }

}
