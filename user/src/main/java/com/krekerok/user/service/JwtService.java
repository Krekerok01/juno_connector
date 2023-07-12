package com.krekerok.user.service;

import com.krekerok.user.entity.User;
import io.jsonwebtoken.Claims;
import java.util.function.Function;

public interface JwtService {

    String generateAccessToken(User user);

    String generateRefreshToken(User userDetails);

    boolean isTokenValid(String token, User user);

    String extractEmail(String token);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
}
