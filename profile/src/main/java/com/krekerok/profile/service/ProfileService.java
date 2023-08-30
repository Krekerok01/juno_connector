package com.krekerok.profile.service;

import com.krekerok.profile.dto.request.ProfileRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface ProfileService {

    Long createProfile(ProfileRequest profileRequest, HttpServletRequest httpRequest);
}