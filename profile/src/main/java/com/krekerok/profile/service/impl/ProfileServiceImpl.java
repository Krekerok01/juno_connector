package com.krekerok.profile.service.impl;

import com.krekerok.profile.dto.request.ProfileRequest;
import com.krekerok.profile.entity.Profile;
import com.krekerok.profile.repository.ProfileRepository;
import com.krekerok.profile.service.ProfileService;
import com.krekerok.profile.util.getter.UserInfoGetter;
import com.krekerok.profile.util.mapper.AppMapper;
import com.krekerok.profile.util.security.JwtUtil;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final UserInfoGetter userInfoGetter;
    private final AppMapper appMapper;

    @Override
    public Long createProfile(ProfileRequest profileRequest, HttpServletRequest httpRequest) {

        String userEmail = JwtUtil.getUserEmailFromToken(httpRequest.getHeader(HttpHeaders.AUTHORIZATION));
        Long userRequestId = userInfoGetter.getUserIdByEmail(userEmail);

        Profile profile = appMapper.toProfile(profileRequest);
        profile.setUserId(userRequestId);
        profileRepository.save(profile);
        return profile.getProfileId();
    }
}
