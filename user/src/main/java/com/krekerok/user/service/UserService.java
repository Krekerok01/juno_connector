package com.krekerok.user.service;

import com.krekerok.user.dto.request.RegisterRequest;
import com.krekerok.user.dto.response.UserRegistrationResponse;

public interface UserService {

    UserRegistrationResponse registerUser(RegisterRequest registerRequest, String localization);
}