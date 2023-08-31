package com.krekerok.profile.service.impl;

import com.krekerok.profile.dto.request.ProfileRequest;
import com.krekerok.profile.dto.response.ProfileResponse;
import com.krekerok.profile.entity.Profile;
import com.krekerok.profile.exception.AccessException;
import com.krekerok.profile.exception.EntityExistsException;
import com.krekerok.profile.exception.EntityNotFoundException;
import com.krekerok.profile.repository.ProfileRepository;
import com.krekerok.profile.service.ProfileService;
import com.krekerok.profile.util.getter.UserInfoGetter;
import com.krekerok.profile.util.mapper.AppMapper;
import com.krekerok.profile.util.security.JwtUtil;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final UserInfoGetter userInfoGetter;
    private final AppMapper appMapper;

    @Transactional
    @Override
    public Long createProfile(ProfileRequest profileRequest, HttpServletRequest httpRequest) {

        Long userId = getUserIdFromUserServiceByJWTToken(httpRequest);

        if (profileRepository.existsByUserId(userId))
            throw new EntityExistsException("The profile of this user exists");

        Profile profile = appMapper.toProfile(profileRequest);
        profile.setUserId(userId);
        profileRepository.save(profile);
        return profile.getProfileId();
    }

    @Transactional(readOnly = true)
    @Override
    public ProfileResponse getProfile(Long profileId) {
        Profile profile = profileRepository.findById(profileId)
            .orElseThrow(() -> new EntityNotFoundException("Profile with id " + profileId + " not found"));

         return appMapper.toProfileResponse(profile);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ProfileResponse> getAllProfiles() {
        return profileRepository.findAll().stream()
            .map(appMapper::toProfileResponse)
            .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Long updateProfile(Long profileId, ProfileRequest profileRequest,
        HttpServletRequest httpRequest) {
        Profile profile = profileRepository.findById(profileId)
            .orElseThrow(() -> new EntityNotFoundException("Profile with id " + profileId + " not found"));

        Long userId = getUserIdFromUserServiceByJWTToken(httpRequest);
        if (!userId.equals(profile.getUserId()))
            throw new AccessException("Access denied");

        updateProfileFields(profile, profileRequest);
        profileRepository.save(profile);
        return profile.getProfileId();
    }

    @Transactional
    @Override
    public void deleteProfile(Long profileId, HttpServletRequest httpRequest) {
        Profile profile = profileRepository.findById(profileId)
            .orElseThrow(() -> new EntityNotFoundException("Profile with id " + profileId + " not found"));
        Long userId = getUserIdFromUserServiceByJWTToken(httpRequest);

        if (!userId.equals(profile.getUserId()))
            throw new AccessException("Access denied");

        profileRepository.delete(profile);
    }

    private Long getUserIdFromUserServiceByJWTToken(HttpServletRequest httpRequest){
        String userEmail = JwtUtil.getUserEmailFromToken(httpRequest.getHeader(HttpHeaders.AUTHORIZATION));
        return userInfoGetter.getUserIdByEmail(userEmail);
    }

    private void updateProfileFields(Profile profile, ProfileRequest profileRequest) {
        profile.setUserInformation(profileRequest.getUserInformation());
        profile.setSpecialization(profileRequest.getSpecialization());
        profile.setCountry(profileRequest.getCountry());
        profile.setCity(profileRequest.getCity());
    }
}