-- liquibase formatted sql

-- changeset vmamatsiuk:1
create table if not exists profiles(
    profile_id bigserial not null,
    user_id bigint not null,
    user_information varchar(1000) not null,
    specialization varchar(30) not null,
    country varchar(30),
    city varchar(30),

    constraint profile_pk primary key(profile_id)
);

-- changeset vmamatsiuk:2
create table if not exists jobs(
    job_id bigserial not null,
    profile_id bigint not null,
    company_name varchar(25) not null,
    position varchar(30) not null,
    start_date date not null,
    current_job boolean,
    end_date date,
    additional_info varchar(1000),

    constraint jobs_pk primary key(job_id),
    constraint jobs_profiles_fk foreign key (profile_id) references profiles(profile_id)
);

-- changeset vmamatsiuk:3
create table if not exists educations(
    education_id bigserial not null,
    profile_id bigint not null,
    institution_name varchar(45) not null,
    training_specialty varchar(30) not null,
    start_date date not null,
    end_date date not null,

    constraint educations_pk primary key(education_id),
    constraint educations_profiles_fk foreign key (profile_id) references profiles(profile_id)
);

-- changeset vmamatsiuk:4
create table if not exists resources(
    resource_id bigserial not null,
    profile_id bigint not null,
    resource_type varchar(30) not null,
    resource_name varchar(30) not null,
    resource_link varchar(260) not null,

    constraint resources_pk primary key(resource_id),
    constraint resources_profiles_fk foreign key (profile_id) references profiles(profile_id)
);

-- changeset vmamatsiuk:5
create table if not exists skills(
    skill_id bigserial not null,
    profile_id bigint not null,
    skill_name varchar(30) not null,

    constraint skills_pk primary key(skill_id),
    constraint skills_profiles_fk foreign key (profile_id) references profiles(profile_id)
);