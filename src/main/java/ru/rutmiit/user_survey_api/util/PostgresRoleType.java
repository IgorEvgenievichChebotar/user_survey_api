package ru.rutmiit.user_survey_api.util;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.EnumType;
import ru.rutmiit.user_survey_api.model.enumeration.RoleType;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

public class PostgresRoleType extends EnumType<RoleType> {
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
