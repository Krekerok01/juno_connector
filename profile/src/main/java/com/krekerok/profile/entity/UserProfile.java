package com.krekerok.profile.entity;

import java.net.URL;
import java.util.List;
import java.util.Map;

public class UserProfile {
    private Long userId;

    //изобр

    private String aboutMe;

    private Map<ContactLinks, URL> userContactInfo;

    private List<String> userSkills;

    private List<String> userInterests;

    private List<Source> userSources;

    private Location userLocation;

    private Education userEducation;

    private WorkExperience userWorkExperience;

}
