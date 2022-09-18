insert into survey(title, start_date, end_date, description)
values ('survey1name', '24-02-2019', '12-03-2023', 'survey1description'),
       ('survey2name', '24-03-2020', '12-03-2021', 'survey2description'),
       ('Российский опрос', '24-03-2021', '12-03-2024', 'Опрос для всех россиян!');

insert into question(survey_id, type, text)
VALUES (1, 'TEXT', 'question1text'),
       (1, 'TEXT', 'question2text'),
       (1, 'TEXT', 'question3text'),
       (2, 'TEXT', 'question4text'),
       (2, 'TEXT', 'question5text'),
       (null, 'TEXT', 'question6text'),
       (3, 'ONE_OPTION', 'Какой президент России был самым лучшим?'),
       (3, 'MULTIPLE_OPTIONS', 'question8text');

insert into option(question_id, text)
VALUES (7, 'Путин'),
       (7, 'Медведев'),
       (7, 'Ельцин');

insert into usr(name, email)
VALUES ('IVAN', 'ivan@yandex.ru'),
       ('MARIA', 'maria@mail.ru');

insert into usr_survey(usr_id, survey_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 1);