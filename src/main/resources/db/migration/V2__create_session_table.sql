CREATE TABLE if not exists events
(
    id           VARCHAR(36) PRIMARY KEY,
    name         varchar(255),
    date         date,
    owner_id     varchar(36) REFERENCES users (id),
    created_at   date,
    last_updated date
);

CREATE TABLE if not exists events_user
(
    session_id varchar(36) REFERENCES events (id),
    user_id    varchar(36) references users (id),
    PRIMARY KEY (session_id, user_id)
);