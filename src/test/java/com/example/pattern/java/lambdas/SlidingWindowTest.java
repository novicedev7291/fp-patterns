package com.example.pattern.java.lambdas;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class SlidingWindowTest {
    private final String sentence = "Developers unfamiliar with the technique will have a harder time working with code that uses currying";

    /**
     * Kind of main function disguise as test function :)
     */
    @Test
    void shouldProduceNSizeWordsListOfSentenceWithSlidingWindowApproach() {
        int N = 3;
        final List<String> words = Arrays.asList(sentence.split(" +"));
        int SIZE = words.size();

        final List<List<String>> result = IntStream.range(0, SIZE - N + 1)
                                                    .mapToObj(i -> words.subList(i, i + N))
                                                    .collect(toList());

        result.forEach(System.out::println);
    }
}
