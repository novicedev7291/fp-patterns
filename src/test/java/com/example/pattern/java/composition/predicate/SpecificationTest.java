package com.example.pattern.java.composition.predicate;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class SpecificationTest {
    @Test
    void testCustomPredicateImplementation() {
        //given
        Specification<String> namesSpec = name -> name.length() == 7;

        final List<String> matchingNames =
                Stream.of("Kuldeep", "Sandeep", "Atul", "Reena", "Joseph").filter(namesSpec)
                        .collect(toList());

        assertThat(matchingNames).contains("Kuldeep", "Sandeep");
    }

    @Test
    void shouldPerformXOROnPersons() {
        final List<Person> employees = Arrays.asList(
                Person.of("Josh Butler", 32, 123789.81),
                Person.of("Eva Joseph", 35, 23456.98),
                Person.of("Kristi Shannon", 29, 148502.09)
        );

        Specification<Person> ageGreaterThanThirty = person -> person.getAge() >= 30;
        Specification<Person> salaryMoreThanHundredThousand = person -> person.getSalary() >= 100000;

        Specification<Person> xorAgeAndSalary = ageGreaterThanThirty.xOR(salaryMoreThanHundredThousand);

        final List<Person> result = employees.stream().filter(xorAgeAndSalary)
                .collect(toList());

        assertThat(result).contains(
                Person.of("Eva Joseph", 35, 23456.98),
                Person.of("Kristi Shannon", 29, 148502.09)
        );
    }
}