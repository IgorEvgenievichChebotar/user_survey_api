package ru.rutmiit.user_survey_api.util;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.EnumType;
import ru.rutmiit.user_survey_api.model.enumeration.QuestionType;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class PostgresQuestionType extends EnumType<QuestionType> {
    public void nullSafeSet(
            PreparedStatement st,
            Object value,
            int index,
            SharedSessionContractImplementor session) throws HibernateException, SQLException {

        st.setObject(
                index,
                value != null ?
                        ((Enum<?>) value).name() :
                        null,
                Types.OTHER
        );
    }
}
