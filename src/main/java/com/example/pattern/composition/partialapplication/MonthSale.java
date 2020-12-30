package com.example.pattern.composition.partialapplication;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */

@EqualsAndHashCode(of = {"month","order"})
@Getter
@RequiredArgsConstructor(staticName = "of")
class MonthSale implements Comparable<MonthSale>{
    private final String month;
    private final Double amount;
    private final int order;

    @Override
    public int compareTo(MonthSale o) {
        return Integer.compare(order, o.order);
    }
}
