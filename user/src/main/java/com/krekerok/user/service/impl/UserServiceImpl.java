package com.krekerok.user.service.impl;

import com.krekerok.user.dto.kafka.RegistrationMessageDto;
import com.krekerok.user.dto.kafka.RegistrationPayload;
import com.krekerok.user.dto.request.LoginRequest;
import com.krekerok.user.dto.request.RegisterRequest;
import com.krekerok.user.dto.response.UserLoginResponse;
import com.krekerok.user.dto.response.UserResponse;
import com.krekerok.user.entity.Role;
import com.krekerok.user.entity.User;
import com.krekerok.user.exception.EntityExistsException;
import com.krekerok.user.exception.EntityNotFoundException;
import com.krekerok.user.exception.InvalidCredentialsException;
import com.krekerok.user.kafka.KafkaService;
import com.krekerok.user.mapper.UserMapper;
import com.krekerok.user.repository.UserRepository;
import com.krekerok.user.service.JwtService;
import com.krekerok.user.service.UserService;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
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
    private final KafkaService kafkaService;
    private final ExecutorService executorService;

    @Override
    public UserResponse registerUser(RegisterRequest registerRequest, String localization) {
        log.info("Registration of a new user: {}", registerRequest);
        User user = buildUser(registerRequest, localization);
        if (userRepository.existsByEmail(user.getEmail()))
            throw new EntityExistsException("Email already exists");

        userRepository.save(user);
        sendGreetingMessage(user);
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

    @Override
    public User findUserByEmail(String email) {
        log.info("Getting a user by email: {}", email);
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new EntityNotFoundException("User with email " + email + " not found"));
    }

    @Override
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream()
            .map(userMapper::toUserResponse)
            .collect(Collectors.toList());
    }

    @Override
    public UserResponse findById(Long userId) {
        return userRepository.findById(userId)
            .map(userMapper::toUserResponse)
            .orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found"));
    }

    @Override
    public void deleteById(Long userId) {
        userRepository.delete(findUserById(userId));
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("User with id " + userId + " not found"));
    }

    private User buildUser(RegisterRequest registerRequest, String localization) {
        return User.builder()
            .firstName(registerRequest.getFirstName())
            .lastName(registerRequest.getLastName())
            .password(passwordEncoder.encode(registerRequest.getPassword()))
            .email(registerRequest.getEmail())
            .role(Role.USER)
            .localization(Objects.requireNonNullElse(localization, "EN"))
            .build();
    }

    private void sendGreetingMessage(User user) {
        RegistrationPayload payload = RegistrationPayload.builder()
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .role(user.getRole().toString())
            .build();
        RegistrationMessageDto greetingMessage = RegistrationMessageDto.builder()
            .email(user.getEmail())
            .localization(user.getLocalization())
            .payload(payload)
            .build();
        executorService.submit(() -> kafkaService.sendMessageRegister(greetingMessage));
    }
}