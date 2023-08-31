package com.krekerok.profile.util.security;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

public class JwtUtil {

    public static String getUserEmailFromToken(String token) {
        try {
            String jwt = token.replace("Bearer ", "");
            String jwtWithoutSignature = jwt.substring(0, jwt.lastIndexOf('.') + 1);
            return Jwts.parserBuilder()
                .build()
                .parseClaimsJwt(jwtWithoutSignature)
                .getBody()
                .getSubject();
        } catch (ExpiredJwtException ex) {
            throw new RuntimeException("Token has expired!");
        }
    }

    public static String getUserRoleFromToken(String token) {
        try {
            String jwt = token.replace("Bearer ", "");
            String jwtWithoutSignature = jwt.substring(0, jwt.lastIndexOf('.') + 1);
            return Jwts.parserBuilder()
                .build()
                .parseClaimsJwt(jwtWithoutSignature)
                .getBody()
                .get("role", String.class);
        } catch (ExpiredJwtException ex) {
            throw new RuntimeException("Token has expired!");
        }
    }
}