--liquibase formatted sql

--changeset igorchebotar:1
create table answer
(
    id          bigint generated by default as identity primary key,
    usr_id      bigint references usr (id) on delete SET NULL,
    survey_id   bigint references survey (id) on delete SET NULL,
    question_id bigint references question (id) on delete SET NULL,
    answer      text default (null)
);