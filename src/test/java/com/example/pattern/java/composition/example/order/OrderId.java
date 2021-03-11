package com.example.pattern.java.composition.example.order;

import lombok.Value;

import java.util.UUID;

@Value(staticConstructor = "of")
class OrderId {
    String value;

    public static OrderId random() {
        return of(UUID.randomUUID().toString());
    }
}
