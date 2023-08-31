package com.krekerok.profile.service;

import com.krekerok.profile.dto.request.ProfileRequest;
import javax.servlet.http.HttpServletRequest;

public interface ProfileService {

    Long createProfile(ProfileRequest profileRequest, HttpServletRequest httpRequest);
}