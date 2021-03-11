package com.example.pattern.java.composition.example.order.v2;

import com.example.pattern.core.Failure;
import com.example.pattern.core.Success;
import com.example.pattern.core.Try;
import com.example.pattern.java.composition.example.order.InsufficientInventory;
import com.example.pattern.java.composition.example.order.Item;
import com.example.pattern.java.composition.example.order.OrderStateException;
import com.example.pattern.java.composition.example.order.inventory.InventoryService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.function.Function;

import static com.example.pattern.java.composition.example.order.OrderState.ALLOCATED;
import static com.example.pattern.java.composition.example.order.OrderState.CONFIRMED;
import static com.example.pattern.java.composition.example.order.OrderState.READY_TO_SHIP;
import static java.util.stream.Collectors.toMap;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class OrderService {
    public static Function<InventoryService, Try<Order>> allocate(Order order) {
        return inventoryService -> {
            Map<String, Integer> skuQtyMap = order.getItems()
                                                  .stream()
                                                  .collect(
                                                          toMap(Item::getCode, item -> item.getQty().getValue())
                                                  );

            if (!inventoryService.allocateItems(skuQtyMap))
                return new Failure<>(new InsufficientInventory("Inventory is not sufficient for the order"));
            if (order.getState() != CONFIRMED)
                return new Failure<>(new OrderStateException("Only confirmed order can be allocated"));
            return Success.of(order.copy(ALLOCATED));
        };
    }

    public static Try<Order> moveForShipping(Order order) {
        if(order.getState() != ALLOCATED)
            new Failure<>(new OrderStateException("Only allocated order can be moved to ready to ship"));
        return Success.of(order.copy(READY_TO_SHIP));
    }
}
