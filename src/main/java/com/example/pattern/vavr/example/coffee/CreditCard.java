package com.example.pattern.vavr.example.coffee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class CreditCard {
    public void charge(Double price) {
        log.info("Credit card is charged with amount {} ", price);
    }
}
