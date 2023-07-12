package com.krekerok.user.service;

import com.krekerok.user.dto.request.RegisterRequest;
import com.krekerok.user.dto.response.UserAuthenticationResponse;

public interface UserService {

    UserAuthenticationResponse registerUser(RegisterRequest registerRequest, String localization);
}