package com.example.pattern.core;

import lombok.Value;

import java.math.BigDecimal;

@Value(staticConstructor = "of")
public class Money {
    BigDecimal value;

    public Money add(Money other) {
        BigDecimal newValue = this.value.add(other.value);
        return new Money(newValue);
    }
}
