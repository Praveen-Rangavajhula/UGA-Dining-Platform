CREATE SEQUENCE IF NOT EXISTS _user_seq START WITH 1 INCREMENT BY 50;

CREATE TABLE _user
(
    id        BIGINT NOT NULL,
    firstname VARCHAR(255),
    lastname  VARCHAR(255),
    email     VARCHAR(255),
    password  VARCHAR(255),
    role      VARCHAR(255),
    CONSTRAINT pk__user PRIMARY KEY (id)
);