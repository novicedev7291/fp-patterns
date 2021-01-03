package com.example.pattern.java.composition.predicate;

import lombok.Value;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
@Value(staticConstructor = "of")
public class Person {
    String name;
    int age;
    Double salary;
}
