package com.example.pattern.vavr.example;

import lombok.Value;

import java.util.UUID;

@Value(staticConstructor = "of")
public class AccountId {
    UUID value;
}
