package com.livk.commons.function;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * <p>
 * PresentOrElseHandler
 * </p>
 *
 * @author livk
 */
@FunctionalInterface
public interface Present<T> {

    static <T> Present<T> handler(T t, Predicate<T> predicate) {
        return (action, emptyAction) -> {
            if (predicate.test(t))
                action.accept(t);
            else
                emptyAction.run();
        };
    }

    void present(Consumer<T> action, Runnable emptyAction);

}
