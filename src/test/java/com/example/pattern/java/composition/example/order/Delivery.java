package com.example.pattern.java.composition.example.order;

import com.example.pattern.java.composition.example.order.v1.Order;
import lombok.Value;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Value(staticConstructor = "of")
public class Delivery {
    Customer customer;
    List<DeliveryItem> items;

    public static Delivery of(Order order) {
        List<DeliveryItem> deliveryItems = order.getItems()
                                                .stream()
                                                .map(item -> DeliveryItem.of(item.getCode(), item.getQty()))
                                                .collect(toList());
        return Delivery.of(order.getCustomer(), deliveryItems);
    }

    public static Delivery of(com.example.pattern.java.composition.example.order.v2.Order order) {
        List<DeliveryItem> deliveryItems = order.getItems()
                                                .stream()
                                                .map(item -> DeliveryItem.of(item.getCode(), item.getQty()))
                                                .collect(toList());
        return Delivery.of(order.getCustomer(), deliveryItems);
    }
}
