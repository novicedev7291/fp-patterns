package com.example.pattern.java.composition.example.order.v2;

import com.example.pattern.core.Try;
import com.example.pattern.java.composition.example.order.Delivery;
import com.example.pattern.java.composition.example.order.InsufficientInventory;
import com.example.pattern.java.composition.example.order.Item;
import com.example.pattern.java.composition.example.order.inventory.InventoryService;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import static com.example.pattern.java.composition.example.order.v2.Order.allocate;
import static java.util.stream.Collectors.toMap;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
@RequiredArgsConstructor
class OrderService {
    private final InventoryService inventoryService;

    public Option<Delivery> processForShipping(Order order) {
        Map<String, Integer> skuQtyMap = order.getItems()
                                              .stream()
                                              .collect(
                                                      toMap(Item::getCode, item -> item.getQty().getValue())
                                              );
        if (!inventoryService.allocateItems(skuQtyMap))
            throw new InsufficientInventory("Inventory is not sufficient for the order");

        Try<Order> possibleOrder = allocate(order).flatMap(Order::moveForShipping);

        if(possibleOrder.isFailure()) {
            return Option.none();
        }

        return Option.some(Delivery.of(possibleOrder.get()));
    }
}
