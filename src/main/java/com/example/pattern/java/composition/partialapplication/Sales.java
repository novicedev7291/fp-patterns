package com.example.pattern.java.composition.partialapplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Represent a function which will consume {@link Percent} and calculate tax.
 * <br/>
 * <br/>
 * Function composition visualization:
 * <pre>
 * function from(String fromMonth) {
 *     return (toMonth, Percent) -> {  // X instance with abstract method calculateTax(String, percent)
 *         (toMonth) -> {  // to(String toMonth) default method of X instance
 *             return (percent) -> tax // Sales instance with abstract method calculateTax(percent), which will delegate
 *             //the calculation to X.calculateTax(string, percent)
 *         }
 *
 *         //Here actual calculation is happening
 *     }
 * }
 * </pre>
 *
 * @author <a href="kuldeepyadav7291@gmail.com">Kuldeep</a>
 */
public interface Sales {
    Double calculateTax(Percent percent);

    /**
     * Represent a function which will consume <B>Month Code<B/> i.e <B>JAN<B/> and {@link Percent} and produces total
     * tax. This is supposed to be used internally only.
     */
    interface X {
        /**
         * Applies this function to given arguments.
         * @param toMonth Month code
         * @param percent Percent tax value
         * @return Total tax
         */
        Double calculateTax(String toMonth, Percent percent);

        /**
         * Static factory method which will return {@link Sales}
         * @param toMonth Month Code
         * @return {@link Sales}
         */
        default Sales to(String toMonth) {
            return percent -> calculateTax(toMonth, percent);
        }
    }

    /**
     * Static factory method which will return {@link X}
     * @param fromMonth Month code
     * @return {@link X}
     */
    static X from(String fromMonth) {
        return (toMonth, percent) -> {
            final Path path = Paths.get("data/sales.txt");
            try (Stream<String> lines = Files.lines(path)) {
                Map<String, Integer> monthOrder = new HashMap<>(12);
                monthOrder.put("JAN", 1);
                monthOrder.put("FEB", 2);
                monthOrder.put("MAR", 3);
                monthOrder.put("APR", 4);
                monthOrder.put("MAY", 5);
                monthOrder.put("JUN", 6);
                monthOrder.put("JUL", 7);
                monthOrder.put("AUG", 8);
                monthOrder.put("SEP", 9);
                monthOrder.put("OCT", 10);
                monthOrder.put("NOV", 11);
                monthOrder.put("DEC", 12);

                final List<MonthSale> allSales = lines.map(line -> MonthSale.of(
                        line.substring(0, 3),
                        Double.parseDouble(line.substring(4)),
                        monthOrder.get(line.substring(0, 3))
                )).sorted().collect(toList());

                int from = monthOrder.get(fromMonth);
                int to = monthOrder.get(toMonth);

                final List<MonthSale> totalSales = allSales.subList(from - 1, to);

                final Double total = totalSales.stream().map(MonthSale::getAmount).reduce(0d, Double::sum);
                return total * (percent.getValue()/100.0);

            } catch (IOException ex) {
                throw new IllegalArgumentException();
            }
        };
    }
}
