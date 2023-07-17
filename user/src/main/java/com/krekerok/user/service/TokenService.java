package com.krekerok.user.service;

import com.krekerok.user.dto.response.UserLoginResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface TokenService {

    UserLoginResponse refreshToken(HttpServletRequest request);
}
