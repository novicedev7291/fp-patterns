package com.example.pattern.vavr.example.either;

import lombok.Value;

import java.math.BigInteger;

@Value
public class Money {
    BigInteger value;

    public static Money of(Long value) {
        return new Money(BigInteger.valueOf(value));
    }

    public boolean isLessThanEqualTo(Money other) {
        return value.compareTo(other.value) <= 0;
    }

    public Money minus(Money other) {
        return new Money(value.subtract(other.value));
    }

    public Money add(Money other) {
        return new Money(value.add(other.value));
    }
}
