package com.krekerok.profile.util.mapper;

import com.krekerok.profile.dto.request.ProfileRequest;
import com.krekerok.profile.entity.Profile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppMapper {

    Profile toProfile(ProfileRequest profileRequest);
}