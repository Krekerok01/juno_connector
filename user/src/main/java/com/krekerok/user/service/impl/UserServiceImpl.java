package com.krekerok.user.service.impl;

import com.krekerok.user.dto.request.LoginRequest;
import com.krekerok.user.dto.request.RegisterRequest;
import com.krekerok.user.dto.response.UserLoginResponse;
import com.krekerok.user.dto.response.UserResponse;
import com.krekerok.user.entity.Role;
import com.krekerok.user.entity.User;
import com.krekerok.user.exception.EntityExistsException;
import com.krekerok.user.exception.InvalidCredentialsException;
import com.krekerok.user.mapper.UserMapper;
import com.krekerok.user.repository.UserRepository;
import com.krekerok.user.service.JwtService;
import com.krekerok.user.service.UserService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtService jwtService;

    @Override
    public UserResponse registerUser(RegisterRequest registerRequest, String localization) {
        log.info("Registration of a new user: {}", registerRequest);
        User user = buildUser(registerRequest, localization);
        if (userRepository.existsByUsernameOrEmail(user.getUsername(), user.getEmail()))
            throw new EntityExistsException("Username or email already exists");

        userRepository.save(user);

        return userMapper.toUserResponse(user);
    }

    @Override
    @Transactional
    public UserLoginResponse loginUser(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
            .orElseThrow(() -> new InvalidCredentialsException("Invalid login or password"));
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return UserLoginResponse.builder()
                .accessToken(jwtService.generateAccessToken(user))
                .refreshToken(jwtService.generateRefreshToken(user))
                .build();
        } else {
            throw new InvalidCredentialsException("Invalid login or password");
        }
    }

    //error
    @Override
    public User findUserByEmail(String email) {
        log.info("Getting a user by email: {}", email);
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Exception email"));
    }

    private User buildUser(RegisterRequest registerRequest, String localization) {
        return User.builder()
            .username(registerRequest.getUsername())
            .password(passwordEncoder.encode(registerRequest.getPassword()))
            .email(registerRequest.getEmail())
            .role(Role.USER)
            .localization(Objects.requireNonNullElse(localization, "EN"))
            .build();
    }
}