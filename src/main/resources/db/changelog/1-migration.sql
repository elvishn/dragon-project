-- liquibase formatted sql

-- changeset elena:1
CREATE TABLE dragons (
    id VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(100),
    age INTEGER,
    health INTEGER,
    weight INTEGER,
    hunger INTEGER,
    power INTEGER
);

