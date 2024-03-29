package com.example.pattern.java.composition.example.order.v3;

import com.example.pattern.core.Failure;
import com.example.pattern.core.Success;
import com.example.pattern.core.Try;
import com.example.pattern.core.TryReader;
import com.example.pattern.java.composition.example.order.InsufficientInventory;
import com.example.pattern.java.composition.example.order.Item;
import com.example.pattern.java.composition.example.order.OrderState;
import com.example.pattern.java.composition.example.order.OrderStateException;
import com.example.pattern.java.composition.example.order.inventory.InventoryService;
import com.example.pattern.java.composition.example.order.v2.Order;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

import static com.example.pattern.java.composition.example.order.OrderState.PACKED;
import static com.example.pattern.java.composition.example.order.OrderState.READY_TO_SHIP;
import static java.util.stream.Collectors.toMap;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class OrderService {
    public static TryReader<InventoryService, Order> allocate(Order order) {
        return new TryReader<>(
                inventoryService -> {
                    Map<String, Integer> skuQtyMap = order.getItems()
                                                          .stream()
                                                          .collect(
                                                                  toMap(Item::getCode, item -> item.getQty().getValue())
                                                          );

                    if (!inventoryService.allocateItems(skuQtyMap))
                        return new Failure<>(new InsufficientInventory("Inventory is not sufficient for the order"));
                    if (order.getState() != OrderState.CONFIRMED)
                        return new Failure<>(new OrderStateException("Only confirmed order can be allocated"));
                    return Success.of(order.copy(OrderState.ALLOCATED));
        });
    }

    public static TryReader<InventoryService, Order> pack(Order order) {
        return new TryReader<>(inventoryService -> {
            Map<String, Integer> skuQtyMap = order.getItems()
                                                  .stream()
                                                  .collect(
                                                          toMap(Item::getCode, item -> item.getQty().getValue())
                                                  );
            if(!inventoryService.packItems(skuQtyMap))
                return new Failure<>(new IllegalStateException("Some problem occurred while packing"));
            if(order.getState() != OrderState.ALLOCATED) {
                return new Failure<>(new OrderStateException("Only allocated order can be packed"));
            }
            return Success.of(order.copy(OrderState.PACKED));
        });
    }

    public static Try<Order> moveForShipping(Order order) {
        if(order.getState() != PACKED)
            new Failure<>(new OrderStateException("Only packed order can be moved to ready to ship"));
        return Success.of(order.copy(READY_TO_SHIP));
    }
}
