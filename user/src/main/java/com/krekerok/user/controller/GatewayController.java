package com.krekerok.user.controller;

import com.krekerok.user.entity.User;
import com.krekerok.user.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/gateway")
@RequiredArgsConstructor
public class GatewayController {

    private final UserService userService;

    @GetMapping("/check/{email}")
    public User checkUserByEmail(@PathVariable String email) {
        log.info("Checking user email - " + email);
        return userService.findUserByEmail(email);
    }
}
