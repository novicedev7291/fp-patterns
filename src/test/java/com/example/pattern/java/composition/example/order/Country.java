package com.example.pattern.java.composition.example.order;

import lombok.Value;

@Value(staticConstructor = "of")
public class Country {
    String value;
}
