package com.krekerok.user.mapper;

import com.krekerok.user.dto.response.UserRegistrationResponse;
import com.krekerok.user.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserRegistrationResponse toUserRegistrationResponse(User user);
}