CREATE TABLE if not exists users
(
    id           VARCHAR(36) PRIMARY KEY,
    email        VARCHAR(255)        NOT NULL,
    username     VARCHAR(255) UNIQUE NOT NULL,
    first_name   VARCHAR(255),
    last_name    VARCHAR(255),
    password     VARCHAR(255)        NOT NULL,
    token        VARCHAR(255)        NOT NULL,
    role         VARCHAR(255)        NOT NULL,
    created_at   date,
    last_updated date
);