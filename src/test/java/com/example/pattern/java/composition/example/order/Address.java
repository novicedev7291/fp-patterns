package com.example.pattern.java.composition.example.order;

import lombok.Value;

@Value(staticConstructor = "of")
public class Address {
    Street line1;
    Street line2;
    City city;
    State state;
    Country country;
}
