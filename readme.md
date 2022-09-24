# user survey system:

| request |         endpoint         |                  description                  | authorization |
|:-------:|:------------------------:|:---------------------------------------------:|:-------------:|
|  POST   |        /auth/reg         |                 registration                  |      all      |
|  POST   |       /auth/login        |                 authorization                 |      all      |
|  POST   |         /survey          |                adding a survey                |     ADMIN     |
|  PATCH  |       /survey/{id}       |       changing a survey with a given id       |     ADMIN     |
| DELETE  |       /survey/{id}       |     deleting a survey with a specified id     |     ADMIN     |
|  POST   |       /survey/{id}       | adding questions to a survey with a given id  |     ADMIN     |
|  POST   | /question?survey_id={id} | adding a question to a survey with a given id |     ADMIN     |
|  PATCH  |      /question/{id}      |      changing a question with a given id      |     ADMIN     |
| DELETE  |      /question/{id}      |      deleting a question with a given id      |     ADMIN     |
|   GET   |   /survey?user_id={id}   |     getting completed surveys by user id      |      all      |
|   GET   |         /survey          |              getting all surveys              |      all      |
|   GET   |   /survey?active=true    |            getting active surveys             |      all      |
|   GET   |       /survey/{id}       |       getting a survey with a given id        |      all      |
|  POST   |    /survey/{id}/pass     |   passing the survey with the specified id    |      all      |