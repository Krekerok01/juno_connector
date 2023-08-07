package com.krekerok.user.service;

import com.krekerok.user.dto.request.LoginRequest;
import com.krekerok.user.dto.request.RegisterRequest;
import com.krekerok.user.dto.request.ChangePasswordRequest;
import com.krekerok.user.dto.request.UpdateUserRequest;
import com.krekerok.user.dto.response.UserLoginResponse;
import com.krekerok.user.dto.response.UserResponse;
import com.krekerok.user.entity.User;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public interface UserService {

    UserResponse registerUser(RegisterRequest registerRequest, String localization);

    UserLoginResponse loginUser(LoginRequest loginRequest);

    User findUserByEmail(String email);

    List<UserResponse> findAll();

    UserResponse findById(Long userId);

    UserResponse updateUser(Long userId, UpdateUserRequest updateUserRequest);

    void deleteById(Long userId);

    UserResponse changePassword(ChangePasswordRequest changePasswordRequest, HttpServletRequest httpRequest);
}