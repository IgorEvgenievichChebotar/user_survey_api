package ru.rutmiit.user_survey_api.mapper;

import lombok.SneakyThrows;

import java.util.Collection;

public class ReflectionFieldMapper {
    @SneakyThrows
    public static void mapNonNullFields(Object source, Object target) {

        var sourceFields = source.getClass().getDeclaredFields();
        var targetFields = target.getClass().getDeclaredFields();

        for (int sfCounter = 0; sfCounter < sourceFields.length; sfCounter++) {

            var sourceField = sourceFields[sfCounter];
            var targetField = targetFields[sfCounter];

            sourceField.trySetAccessible();
            targetField.trySetAccessible();

            try {
                var sourceFieldValue = sourceField.get(source);
                var targetFieldValue = targetField.get(target);

                if (Collection.class.isAssignableFrom(sourceField.getType()))
                    continue;

                if (sourceFieldValue != null)
                    targetField.set(target, sourceFieldValue);

                sourceField.setAccessible(false);
                targetField.setAccessible(false);

            } catch (IllegalAccessException ignore) {

            }
        }
    }
}
