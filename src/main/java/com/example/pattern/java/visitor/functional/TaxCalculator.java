package com.example.pattern.java.visitor.functional;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
public interface TaxCalculator<R> {
    interface Z<T,R> {
        Object get(int index);

        default Class<T> type() {
            return (Class<T>) get(0);
        }

        default Y<R> previous() {
            return (Y<R>) get(1);
        }

        default Y<R> execute(Function<T, R> function) {
            return previous().andThen(builder -> builder.register(type(), function.compose(type()::cast)));
        }
    }

    interface Y<R> extends TaxCalculatorInitializer<R> {
        default <T> Z<T,R> forType(Class<T> type) {
            return index -> index == 0 ? type : this;
        }

        default Y<R> andThen(Y<R> after) {
            return builder -> { this.accept(builder); after.accept(builder); };
        }
    }

    interface X<T,R> {
        Class<T> type();

        default Y<R> execute(Function<T,R> function) {
            return builder -> builder.register(type(), function.compose(type()::cast));
        }
    }

    static <T, R> X<T,R> forType(Class<T> type) {
        return () -> type;
    }

    R calculateOn(Object item);

    static <R> TaxCalculator<R> of(TaxCalculatorInitializer<R> taxCalculatorInitializer) {
        Map<Class<?>, Function<Object, R>> registry = new HashMap<>();
        TaxCalculatorBuilder<R> taxCalculatorBuilder = registry::put;
        taxCalculatorInitializer.init(taxCalculatorBuilder);
        return item -> registry.get(item.getClass()).apply(item);
    }
}
