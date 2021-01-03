package com.example.pattern.vavr.example.either;

import lombok.Value;

import java.util.UUID;

@Value(staticConstructor = "of")
public class AccountId {
    UUID value;
}
