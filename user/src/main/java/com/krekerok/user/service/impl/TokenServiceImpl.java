package com.krekerok.user.service.impl;

import com.krekerok.user.dto.response.UserLoginResponse;
import com.krekerok.user.entity.RefreshToken;
import com.krekerok.user.entity.User;
import com.krekerok.user.exception.InvalidTokenException;
import com.krekerok.user.repository.RefreshTokenRepository;
import com.krekerok.user.service.TokenService;
import com.krekerok.user.service.UserService;
import com.krekerok.user.util.JwtUtil;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final UserService userService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;

    @Override
    public UserLoginResponse refreshToken(HttpServletRequest request) {
        log.info("Refreshing a token.");
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String refreshToken;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            refreshToken = authHeader.substring("Bearer ".length());
            checkToken(refreshToken);
            User user = userService.findUserByEmail(jwtUtil.extractEmail(refreshToken));
            saveOldToken(refreshToken, user);
            if (jwtUtil.isTokenValid(refreshToken, user)) {
                String accessToken = jwtUtil.generateAccessToken(user);
                String newRefreshToken = jwtUtil.generateRefreshToken(user);
                return buildUserLoginResponse(accessToken, newRefreshToken);
            }
        }
        return null;
    }

    private void checkToken(String refreshToken) {
        Optional<RefreshToken> checkedToken = refreshTokenRepository.findByToken(refreshToken);
        if (checkedToken.isPresent()){
            throw new InvalidTokenException("Refresh token has been used");
        }
    }

    private void saveOldToken(String refreshToken, User user) {
        RefreshToken token = RefreshToken.builder()
            .token(refreshToken)
            .user(user)
            .expirationTime(LocalDateTime.now())
            .build();
        refreshTokenRepository.save(token);
    }

    private UserLoginResponse buildUserLoginResponse(String accessToken, String refreshToken) {
        return UserLoginResponse.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .build();
    }
}