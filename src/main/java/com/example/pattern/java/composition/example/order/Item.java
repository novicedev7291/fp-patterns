package com.example.pattern.java.composition.example.order;

import com.example.pattern.core.Money;
import lombok.Value;

@Value(staticConstructor = "of")
public class Item {
    String code;
    Quantity qty;
    Money price;
    Money tax;
    Money total;
}
