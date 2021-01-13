package com.example.pattern.java.composition.store;

import lombok.Value;

import java.util.List;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
@Value
class Cart {
    List<Item> items;
}
