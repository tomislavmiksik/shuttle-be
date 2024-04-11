CREATE TABLE if not exists events
(
    id           uuid PRIMARY KEY,
    name         varchar(255),
    date         date,
    owner_id     uuid REFERENCES users (id),
    created_at   date,
    last_updated date
);

CREATE TABLE if not exists events_user
(
    session_id uuid REFERENCES events (id),
    user_id    uuid references users (id),
    PRIMARY KEY (session_id, user_id)
);