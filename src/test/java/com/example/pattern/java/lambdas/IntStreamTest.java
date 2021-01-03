package com.example.pattern.java.lambdas;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class IntStreamTest {
    private final String sentence = "Developers unfamiliar with the technique will have a harder time working with code that uses currying";

    @Test
    void shouldPartitionGivenSentenceIntoNSizeMultipleEqualNumberListOfWords() {
        int N = 3;
        final List<String> words = Arrays.asList(sentence.split(" +"));
        int SIZE = words.size();

        final List<List<String>> result = IntStream.range(0, SIZE/N)
                .mapToObj(i -> words.subList(N * i, N * (i + 1)))
                .collect(toList());

        assertThat(result)
                .contains(
                        Arrays.asList("Developers", "unfamiliar", "with"),
                        Arrays.asList("the", "technique", "will"),
                        Arrays.asList("have", "a", "harder"),
                        Arrays.asList("time", "working", "with"),
                        Arrays.asList("code", "that", "uses")
                );
    }

    @Test
    void shouldPartitionGivenSentenceIntoNSizeMultipleListOfWords() {
        int N = 3;
        final List<String> words = Arrays.asList(sentence.split(" +"));
        int SIZE = words.size();

        final List<List<String>> result = IntStream.range(0, (SIZE+N-1)/N)
                .mapToObj(i -> words.subList(N * i, Math.min(N * (i + 1), SIZE)))
                .collect(toList());

        assertThat(result)
                .contains(
                        Arrays.asList("Developers", "unfamiliar", "with"),
                        Arrays.asList("the", "technique", "will"),
                        Arrays.asList("have", "a", "harder"),
                        Arrays.asList("time", "working", "with"),
                        Arrays.asList("code", "that", "uses"),
                        Collections.singletonList("currying")
                );
    }
}
