package com.krekerok.user.service.impl;

import com.krekerok.user.dto.request.RegisterRequest;
import com.krekerok.user.dto.response.UserAuthenticationResponse;
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
    private final JwtService jwtService;

    @Override
    public UserAuthenticationResponse registerUser(RegisterRequest registerRequest, String localization) {

        return null;
    }
}