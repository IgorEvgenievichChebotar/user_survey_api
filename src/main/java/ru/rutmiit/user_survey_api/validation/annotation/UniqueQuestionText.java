package ru.rutmiit.user_survey_api.validation.annotation;

import ru.rutmiit.user_survey_api.validation.UniqueQuestionTextValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = UniqueQuestionTextValidator.class)
public @interface UniqueQuestionText {
    String message() default "this question.text already exists";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}