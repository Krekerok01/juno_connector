package com.krekerok.user.service;

import com.krekerok.user.dto.request.LoginRequest;
import com.krekerok.user.dto.request.RegisterRequest;
import com.krekerok.user.dto.response.UserLoginResponse;
import com.krekerok.user.dto.response.UserResponse;
import com.krekerok.user.entity.User;
import java.util.List;

public interface UserService {

    UserResponse registerUser(RegisterRequest registerRequest, String localization);

    UserLoginResponse loginUser(LoginRequest loginRequest);

    User findUserByEmail(String email);

    List<UserResponse> findAll();

    UserResponse findById(Long userId);

    void deleteById(Long userId);
}