package com.example.pattern.composition.predicate;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
@FunctionalInterface
public interface Specification<T> extends java.util.function.Predicate<T> {
    default Specification<T> xOR(Specification<T> other) {
        return t -> test(t) ^ other.test(t);
    }
}
