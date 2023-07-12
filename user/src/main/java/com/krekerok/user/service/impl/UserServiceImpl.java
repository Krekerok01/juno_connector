package com.krekerok.user.service.impl;

import com.krekerok.user.dto.request.RegisterRequest;
import com.krekerok.user.dto.response.UserRegistrationResponse;
import com.krekerok.user.entity.Role;
import com.krekerok.user.entity.User;
import com.krekerok.user.mapper.UserMapper;
import com.krekerok.user.repository.UserRepository;
import com.krekerok.user.service.JwtService;
import com.krekerok.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtService jwtService;

    @Override
    public UserRegistrationResponse registerUser(RegisterRequest registerRequest, String localization) {
        User user = buildUser(registerRequest, localization);
        if (userRepository.existsByUsernameOrEmail(user.getUsername(), user.getEmail()))
            throw new IllegalArgumentException("Username or email already exists");

        userRepository.save(user);

        return userMapper.toUserRegistrationResponse(user);
    }

    private User buildUser(RegisterRequest registerRequest, String localization) {
        return User.builder()
            .username(registerRequest.getUsername())
            .password(passwordEncoder.encode(registerRequest.getPassword()))
            .email(registerRequest.getEmail())
            .role(Role.USER)
            .localization(localization)
            .build();
    }
}