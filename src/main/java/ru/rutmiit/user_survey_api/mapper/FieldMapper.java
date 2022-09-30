package ru.rutmiit.user_survey_api.mapper;

import lombok.SneakyThrows;
import ru.rutmiit.user_survey_api.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.Collection;

public class FieldMapper {
    @SneakyThrows
    public static <T> void mapNonNullFields(T source, T target) {

        Field[] sourceFields = source.getClass().getDeclaredFields();
        Field[] targetFields = target.getClass().getDeclaredFields();

        for (int sfCounter = 0; sfCounter < sourceFields.length; sfCounter++) {

            Field sourceField = sourceFields[sfCounter];
            Field targetField = targetFields[sfCounter];

            sourceField.trySetAccessible();
            targetField.trySetAccessible();

            try {
                Object sourceFieldValue = sourceField.get(source);
                Object targetFieldValue = targetField.get(target);

                if (sourceField.get(source) instanceof Collection<?> sourceCollection &&
                        targetField.get(target) instanceof Collection<?> targetCollection) {

                    CollectionUtils.zip(sourceCollection, targetCollection, FieldMapper::mapNonNullFields);

                } else if (sourceFieldValue != null) {
                    targetField.set(target, sourceFieldValue);
                }

                sourceField.setAccessible(false);
                targetField.setAccessible(false);

            } catch (IllegalAccessException ignore) {

            }
        }
    }
}
