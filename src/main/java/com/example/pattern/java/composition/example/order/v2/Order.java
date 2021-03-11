package com.example.pattern.java.composition.example.order.v2;

import com.example.pattern.core.Money;
import com.example.pattern.java.composition.example.order.Customer;
import com.example.pattern.java.composition.example.order.Item;
import com.example.pattern.java.composition.example.order.OrderId;
import com.example.pattern.java.composition.example.order.OrderState;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.pattern.java.composition.example.order.OrderId.random;
import static com.example.pattern.java.composition.example.order.OrderState.CONFIRMED;
import static java.math.BigDecimal.ZERO;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Order {
    private final OrderId id;
    private final OrderState state;
    private final Customer customer;
    private final List<Item> items;
    private final Money tax;
    private final Money total;

    public static Order with(Customer customer, List<Item> items) {
        Money tax = items.stream().map(Item::getTax).reduce(Money.of(ZERO), Money::add);
        Money total = items.stream().map(Item::getTotal).reduce(Money.of(ZERO), Money::add);
        return new Order(random(), CONFIRMED, customer, items, tax, total);
    }

    public Order copy(OrderState newState) {
        return new Order(id, newState, customer, items, tax, total);
    }

}
