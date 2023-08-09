-- liquibase formatted sql

-- changeset vmamatsiuk:1
create sequence questions_seq start 1 increment 1;

-- changeset vmamatsiuk:2
create table if not exists questions(
    question_id bigserial not null,
    author_id bigint not null,
    open_for_discussion boolean not null,
    question_text varchar(1000) not null,
    opening_date timestamp not null,
    closing_date timestamp,
    modification_date timestamp not null,

    constraint questions_pk primary key(question_id)
);

-- changeset vmamatsiuk:3
create sequence comments_seq start 1 increment 1;

-- changeset vmamatsiuk:4
create table if not exists comments(
    comment_id bigserial not null,
    author_id bigint not null,
    question_id bigint not null,
    comment_text varchar(500) not null,
    creation_date timestamp not null,
    modification_date timestamp not null,

    constraint comments_pk primary key(comment_id),
    constraint comments_questions_fk foreign key (question_id) references questions(question_id)
);