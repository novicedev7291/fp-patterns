package com.example.pattern.java.composition.partialapplication;

import lombok.Value;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
@Value(staticConstructor = "of")
class Percent {
    Double value;
}