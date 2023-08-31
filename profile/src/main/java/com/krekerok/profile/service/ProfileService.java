package com.krekerok.profile.service;

import com.krekerok.profile.dto.request.ProfileRequest;
import com.krekerok.profile.dto.response.ProfileResponse;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public interface ProfileService {

    Long createProfile(ProfileRequest profileRequest, HttpServletRequest httpRequest);

    ProfileResponse getProfile(Long profileId);

    List<ProfileResponse> getAllProfiles();

    Long updateProfile(Long profileId, ProfileRequest profileRequest, HttpServletRequest httpRequest);

    void deleteProfile(Long profileId, HttpServletRequest httpRequest);
}