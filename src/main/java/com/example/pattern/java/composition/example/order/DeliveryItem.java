package com.example.pattern.java.composition.example.order;

import lombok.Value;

@Value(staticConstructor = "of")
public class DeliveryItem {
    String code;
    Quantity qty;
}
