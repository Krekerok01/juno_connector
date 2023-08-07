package com.krekerok.profile.entity;

import org.apache.commons.lang3.tuple.Pair;

import java.time.Year;
import java.util.List;

public class WorkExperience {
    private String companyName;

    private Location companyLocation;

    private String position;

    private Pair<Year, Year> yearsOfWork;

    private List<String> skills;

    private String additionalInfo;


}
