package ru.rutmiit.user_survey_api.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.BiConsumer;

public class CollectionUtils { // zip operations
    public static <T,U> void forEachOfBoth(Collection<T> ct, Collection<U> cu, BiConsumer<T, U> consumer) {
        Iterator<T> it = ct.iterator();
        Iterator<U> iu = cu.iterator();
        while (it.hasNext() && iu.hasNext()) {
            consumer.accept(it.next(), iu.next());
        }
    }

    public static <T,U> void forEachOfBoth(T[] ct, U[] cu, BiConsumer<T, U> consumer) {
        Iterator<T> it = Arrays.stream(ct).iterator();
        Iterator<U> iu = Arrays.stream(cu).iterator();
        while (it.hasNext() && iu.hasNext()) {
            consumer.accept(it.next(), iu.next());
        }
    }
}
