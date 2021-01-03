package com.example.pattern.java.lambdas;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static java.util.stream.Collectors.toList;

/**
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
class PartitioningTest {
    private final String sentence = "Developers unfamiliar with the technique will have a harder time working with code that uses currying";

    @Test
    void shouldPartitionSentenceIntoSublistOfWordsOfNonDecreasingLength() {
        List<String> words = asList(sentence.split(" +"));
        List<List<String>> result = split(words);

        BDDAssertions.assertThat(result).containsExactlyInAnyOrder(
                asList("Developers", "unfamiliar"),
                singletonList("with"),
                asList("the", "technique"),
                asList("will", "have"),
                asList("a", "harder"),
                asList("time", "working"),
                asList("with","code", "that", "uses", "currying")
        );
    }

    private List<List<String>> split(List<String> words) {
        int SIZE = words.size();
        final List<Integer> breaks = IntStream.range(1, SIZE)
                                               .filter(i -> words.get(i-1).length() > words.get(i).length())
                                               .boxed()
                                               .collect(toList());
        breaks.add(0,0);
        breaks.add(words.size());

        return IntStream.range(0, breaks.size()-1)
                .mapToObj(i -> words.subList(breaks.get(i), breaks.get(i+1)))
                .collect(toList());
    }
}
