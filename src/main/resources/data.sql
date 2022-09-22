insert into survey(title, start_date, end_date, description)
values ('Яблоки', '24-02-2019', '12-03-2020', 'Давайте поговорим о яблоках'),
       ('Российский опрос', '24-03-2021', '12-03-2024', 'Опрос для всех россиян!');

insert into question(survey_id, type, text)
VALUES (2, 'ONE_OPTION', 'Какой президент России был самым лучшим?'),
       (2, 'TEXT', 'Что можете сказать о Ельцине?'),
       (1, 'MULTIPLE_OPTIONS', 'Какие сорта яблок предпочитаете больше всего?');

insert into option(question_id, text)
VALUES (1, 'Путин'),
       (1, 'Медведев'),
       (1, 'Ельцин'),
       (3, 'Красные'),
       (3, 'Зеленые'),
       (3, 'Желтые');

insert into usr(name, email)
VALUES ('IVAN', 'ivan@yandex.ru'),
       ('MARIA', 'maria@mail.ru');