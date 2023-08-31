package com.krekerok.profile.controller;

import com.krekerok.profile.dto.request.ProfileRequest;
import com.krekerok.profile.dto.response.ProfileResponse;
import com.krekerok.profile.service.ProfileService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @GetMapping("/{profileId}")
    public ProfileResponse getProfile(@PathVariable("profileId") Long profileId) {
        return profileService.getProfile(profileId);
    }

    @GetMapping
    public List<ProfileResponse> getAllProfiles() {
        return profileService.getAllProfiles();
    }

    @PutMapping("/{profileId}")
    public Long updateProfile(@PathVariable("profileId") Long profileId,
                              @RequestBody @Valid ProfileRequest profileRequest,
                              HttpServletRequest httpRequest) {
        return profileService.updateProfile(profileId, profileRequest, httpRequest);
    }

    @DeleteMapping("/{profileId}")
    public void deleteProfile(@PathVariable("profileId") Long profileId, HttpServletRequest httpRequest) {
        profileService.deleteProfile(profileId, httpRequest);
    }
}