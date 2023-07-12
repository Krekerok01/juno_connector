-- liquibase formatted sql

-- changeset vmamatsiuk:1
create sequence users_seq start 1 increment 1;

create table if not exists users(
    user_id bigserial not null,
    username varchar(30) not null unique,
    password varchar(200) not null,
    email varchar(256) not null unique,
    role varchar (10) not null,
    localization varchar(20) not null,

    constraint users_pk primary key(user_id)
);

-- changeset vmamatsiuk:2
create index idx_username_and_email on users (username, email);