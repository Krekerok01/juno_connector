-- liquibase formatted sql

-- changeset vmamatsiuk:1
create sequence users_seq start 1 increment 1;

create table if not exists users(
    user_id bigserial not null,
    first_name varchar(60) not null,
    last_name varchar(60) not null,
    password varchar(200) not null,
    email varchar(256) not null unique,
    role varchar (10) not null,
    localization varchar(20) not null,

    constraint users_pk primary key(user_id)
);

-- changeset vmamatsiuk:2
create index idx_email on users (email);

-- changeset vmamatsiuk:3
create sequence refresh_tokens_seq start 1 increment 1;

create table if not exists refresh_tokens(
    refresh_token_id bigserial not null,
    token varchar(255) not null unique,
    expiration_time timestamp,
    user_id bigint references users(user_id) on delete cascade,

    constraint refresh_tokens_pk primary key(refresh_token_id)
);