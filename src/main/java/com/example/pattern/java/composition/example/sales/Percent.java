package com.example.pattern.java.composition.example.sales;

import lombok.Value;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
@Value(staticConstructor = "of")
class Percent {
    Double value;
}
