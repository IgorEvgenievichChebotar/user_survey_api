insert into survey(title, start_date, end_date, description)
values ('survey for parents', '24-02-2019', '12-03-2020', '...'),
       ('survey for motorists', '24-03-2021', '12-03-2024', '...');

insert into question(survey_id, type, text)
VALUES (1, 'ONE_OPTION', 'how many children do you have?'),
       (2, 'TEXT', 'what is your favorite car'),
       (1, 'TEXT', 'what do you feed your children?');

insert into option(question_id, text)
VALUES (1, '1'),
       (1, '2'),
       (1, '3'),
       (1, 'more');

insert into usr(name, email)
VALUES ('Anatoly', 'anatoly@gmail.com'),
       ('Maria', 'maria@mail.com');

insert into credential(username, password, role)
VALUES ('admin', '{bcrypt}$2a$12$LlGBRuoYhqQpXxWwRoHZbeBfdb1siVIaVN0jGbLghKBYJL6ZAbOuG', 'ADMIN');

insert into answer(usr_id, survey_id, question_id, answer)
VALUES (1, 1, 1, '2'),
       (1, 1, 3, 'i feed my children potatoes and bananas'),
       (2, 2, 2, 'Lamborghini Hurricane');

