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

        CollectionUtils.forEachOfBoth(sourceFields, targetFields, (sf, tf) -> {
            sf.trySetAccessible();
            tf.trySetAccessible();

            try {
                Object sfValue = sf.get(source);
                Object tfValue = tf.get(target);

                if (sfValue instanceof Collection<?> sourceCol &&
                        tfValue instanceof Collection<?> targetCol)
                    CollectionUtils.forEachOfBoth(sourceCol, targetCol, FieldMapper::mapNonNullFields);

                else if (sfValue instanceof Object[] sourceArr &&
                        tfValue instanceof Object[] targetArr)
                    CollectionUtils.forEachOfBoth(sourceArr, targetArr, FieldMapper::mapNonNullFields);

                else if (sfValue != null)
                    tf.set(target, sfValue);

            } catch (IllegalAccessException ignored) {

            } finally {
                sf.setAccessible(false);
                tf.setAccessible(false);
            }
        });

    }
}
