package com.krekerok.user.service;

import com.krekerok.user.dto.response.UserLoginResponse;
import javax.servlet.http.HttpServletRequest;

public interface TokenService {

    UserLoginResponse refreshToken(HttpServletRequest request);
}
