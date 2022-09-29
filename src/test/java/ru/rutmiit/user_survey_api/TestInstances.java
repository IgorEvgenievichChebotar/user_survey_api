package ru.rutmiit.user_survey_api;

import ru.rutmiit.user_survey_api.model.*;
import ru.rutmiit.user_survey_api.model.enumeration.QuestionType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestInstances {
    public static final Question QUESTION1 = Question.builder()
            .id(1L)
            .text("text1")
            .type(QuestionType.TEXT)
            .options(new ArrayList<>())
            .build();

    public static final Question QUESTION2 = Question.builder()
            .id(2L)
            .text("text2")
            .type(QuestionType.ONE_OPTION)
            .options(List.of(new Option("1"), new Option("2"), new Option("3")))
            .build();

    public static final Question QUESTION3 = Question.builder()
            .id(3L)
            .text("text3")
            .type(QuestionType.MULTIPLE_OPTIONS)
            .options(List.of(new Option("4"), new Option("5")))
            .build();

    public static final Question QUESTION4 = Question.builder()
            .id(4L)
            .text("text4")
            .type(QuestionType.TEXT)
            .options(new ArrayList<>())
            .build();

    public static final Survey SURVEY1 = Survey.builder()
            .id(1L)
            .title("SurveyTitle1")
            .startDate(LocalDate.of(2020, 1, 20))
            .endDate(LocalDate.of(2023, 8, 10))
            .description("SurveyDescription1")
            .questions(List.of(QUESTION1, QUESTION2))
            .build();

    public static final Survey SURVEY2 = Survey.builder()
            .id(2L)
            .title("SurveyTitle2")
            .startDate(LocalDate.of(2019, 1, 10))
            .endDate(LocalDate.of(2021, 8, 13))
            .description("some description...")
            .questions(List.of(QUESTION3, QUESTION4))
            .build();

    public static final Usr USER1 = Usr.builder()
            .id(1L)
            .name("USER1")
            .email("user1@mail.com")
            .build();

    public static final Usr USER2 = Usr.builder()
            .id(2L)
            .name("USER2")
            .email("user2@mail.com")
            .build();

    public static final Answer ANSWER1 = Answer.builder()
            .id(1L)
/*            .usr(USER1)
            .question(QUESTION1)
            .survey(SURVEY1)*/
            .answer("answer")
            .build();

    public static final Answer ANSWER2 = Answer.builder()
            .id(2L)
/*            .usr(USER2)
            .question(QUESTION2)
            .survey(SURVEY2)*/
            .answer("2")
            .build();

    public static final Answer ANSWER3 = Answer.builder()
            .id(3L)
/*            .usr(USER1)
            .question(QUESTION3)
            .survey(SURVEY1)*/
            .answer("4")
            .build();

    public static final Answer ANSWER4 = Answer.builder()
            .id(4L)
/*            .usr(USER2)
            .question(QUESTION4)
            .survey(SURVEY2)*/
            .answer("text")
            .build();
}
