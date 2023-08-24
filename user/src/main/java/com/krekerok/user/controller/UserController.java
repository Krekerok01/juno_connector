package com.krekerok.user.controller;

import com.krekerok.user.dto.request.ChangePasswordRequest;
import com.krekerok.user.dto.request.UpdateUserRequest;
import com.krekerok.user.dto.response.UserResponse;
import com.krekerok.user.service.UserService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserResponse> getAll() {
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public UserResponse findById(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @GetMapping("/email/{userEmail}")
    public Long findByEmail(@PathVariable String userEmail) {
        return userService.findByEmail(userEmail);
    }

    @PatchMapping("/{userId}")
    public UserResponse updateUser(@PathVariable Long userId, @RequestBody @Valid UpdateUserRequest updateUserRequest){
        return userService.updateUser(userId, updateUserRequest);
    }

    @DeleteMapping("/{userId}")
    public void deleteById(@PathVariable Long userId) {
        userService.deleteById(userId);
    }

    @PostMapping("/change-password")
    public UserResponse changePassword(@RequestBody @Valid ChangePasswordRequest changePasswordRequest,
        HttpServletRequest httpServletRequest){
        return userService.changePassword(changePasswordRequest, httpServletRequest);
    }
}