# Система опроса пользователей:

| Запрос |      Конечная точка      |                     Описание                      | Авторизация |
|:------:|:------------------------:|:-------------------------------------------------:|:-----------:|
|  POST  |        /auth/reg         |                    регистрация                    |      -      |
|  POST  |       /auth/login        |                    авторизация                    |      -      |
|  POST  |         /survey          |                 добавление опроса                 |    ADMIN    |
| PATCH  |       /survey/{id}       |          изменение опроса с заданным id           |    ADMIN    |
| DELETE |       /survey/{id}       |           удаление опроса с заданным id           |    ADMIN    |
|  POST  | /question?survey_id={id} |     добавления вопроса к опросу с заданным id     |    ADMIN    |
| PATCH  |      /question/{id}      |          изменение вопроса с заданным id          |    ADMIN    |
| DELETE |      /question/{id}      |          удаление вопроса с заданным id           |    ADMIN    |
|  GET   |   /survey?user_id={id}   |  получение пройденных опросов по id пользователя  |    ADMIN    |
|  GET   |   /survey?active=true    |            получение активных вопросов            | ADMIN, USER |
|  GET   |       /survey/{id}       |          получение опроса с заданным id           | ADMIN, USER |
|  POST  |       /survey/{id}       |         прохождение опроса с заданным id          | ADMIN, USER |
|  POST  |   /survey/passed=true    | получение пройденных данным пользователем опросов | ADMIN, USER |
