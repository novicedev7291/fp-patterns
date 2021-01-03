package com.example.pattern.vavr.example.either;

import lombok.NoArgsConstructor;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class AccountFactory {

    public static Account create(UUID id, Money initBalance) {
        return Account.of(AccountId.of(id), initBalance);
    }
}
