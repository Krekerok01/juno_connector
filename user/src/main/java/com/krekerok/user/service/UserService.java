package com.krekerok.user.service;

import com.krekerok.user.dto.request.LoginRequest;
import com.krekerok.user.dto.request.RegisterRequest;
import com.krekerok.user.dto.response.UserLoginResponse;
import com.krekerok.user.dto.response.UserRegistrationResponse;
import com.krekerok.user.entity.User;

public interface UserService {

    UserRegistrationResponse registerUser(RegisterRequest registerRequest, String localization);

    UserLoginResponse loginUser(LoginRequest loginRequest);

    User findUserByEmail(String email);
}