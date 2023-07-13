package com.krekerok.user.controller;

import com.krekerok.user.dto.request.RegisterRequest;
import com.krekerok.user.dto.response.UserRegistrationResponse;
import com.krekerok.user.service.UserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/register")
    public UserRegistrationResponse register(@Valid @RequestBody RegisterRequest registerRequest,
                                             @RequestHeader(value = "Localization", required = false) String localization){
        return userService.registerUser(registerRequest, localization);
    }
}