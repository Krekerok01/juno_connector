package com.krekerok.profile.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileResponse {
    private Long profileId;
    private Long userId;
    private String userInformation;
    private String specialization;
    private String country;
    private String city;
}
