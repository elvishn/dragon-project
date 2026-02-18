-- liquibase formatted sql

-- changeset elena:2

ALTER TABLE dragons ADD COLUMN peculiarities VARCHAR AFTER name;