-- liquibase formatted sql

-- changeset vmamatsiuk:1
INSERT INTO users (first_name, last_name, password, email, role, localization)
VALUES ('Varvara', 'Mamatsiuk', '$2a$12$tw2pX2njOghWXwPgKAfizedQl/KXijVryWSwwqTeuxA51Rt3kZ5tm',
    'matyush2001@gmail.com', 'USER', 'EN'),
    ('Mikita', 'Mamatsiuk', '$2a$12$tw2pX2njOghWXwPgKAfizedQl/KXijVryWSwwqTeuxA51Rt3kZ5tm',
    'niksson01@gmail.com', 'ADMIN', 'EN');



