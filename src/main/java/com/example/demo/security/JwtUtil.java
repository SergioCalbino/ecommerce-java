package com.example.demo.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class JwtUtil {

    private final String jwtSecret = "claveSuperSecreta";
    private final long accessTokenExpiratiionMs = 15 * 60 * 1000;
    private final long refreshTokenExpirationMs = 7 * 24 * 60 * 60 * 1000;

    public String generateAccessToken(String email, String role){
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpiratiionMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String generateRefreshToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();

    }

    public String getEmailFromToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();

    }

    public String getRoleFromToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().get("role", String.class);

    }



    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.out.println("Token expired" + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported token" + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Malformed token" + e.getMessage());
        } catch (SignatureException e) {
            System.out.println("Invalid sign" + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Token empty" + e.getMessage());
        }
        return false;

    }

}
