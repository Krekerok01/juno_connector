package com.krekerok.profile.controller;

import com.krekerok.profile.dto.request.ProfileRequest;
import com.krekerok.profile.service.ProfileService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping
    public ResponseEntity<Long> createProfile(@RequestBody @Valid ProfileRequest profileRequest, HttpServletRequest httpRequest){
        return new ResponseEntity<>(profileService.createProfile(profileRequest, httpRequest), HttpStatus.CREATED);
    }
}