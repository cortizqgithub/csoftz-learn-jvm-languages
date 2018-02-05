/*----------------------------------------------------------------------------*/
/* Source File:   STREAMSAPI.JAVA                                             */
/* Description:   Java 8 Stream Files API learning                            */
/* Author:        Carlos Adolfo Ortiz Quirós (COQ)                            */
/* Date:          Jan.17/2018                                                 */
/* Last Modified: Jan.22/2018                                                 */
/* Version:       1.1                                                         */
/* Copyright (c), 2018 CSoftZ.                                                */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Jan.17/2018 COQ  File created.
 -----------------------------------------------------------------------------*/
package com.csoftz.study.java8;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Java 8 Stream Files API learning
 *
 * @author Carlos Adolfo Ortiz Quirós (COQ)
 * @version 1.1, Jan.22/2018
 * @since 1.8 (JDK), Jan.18/2018
 */
@SuppressWarnings("unused")
public class StreamOther {
    /**
     * Program entry point.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        //calculateUniqueWords();
        workWithInfiniteStrams();
    }

    /**
     * A way to generate/iterate with infinite streams.
     */
    private static void workWithInfiniteStrams() {
        System.out.println("StreamOther.workWithInfiniteStrams");

        // Iterate
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

        // Compute Fibonacci Series
        System.out.println("Compute the Fibonacci");
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));

        Stream.iterate(new int[]{0, 1},
                t -> new int[]{t[1], t[0] + t[1]})
                .limit(10)
                .map(t -> t[0])
                .forEach(System.out::println);

        // Just Java 9
        //IntStream.iterate(0, n -> n < 100, n -> n + 4).forEach(System.out::println);
        //IntStream.iterate(0, n -> n + 4).takeWhile(n -> n < 100).forEach(System.out::println);

        // Generate
        System.out.println("Generate");
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

        // The following code will illustrate how to implment the Fibonacci series using the
        // 'generate' approach but it uses a side-effect function to compute. Remember
        // side-effect functions are not suitable for parallel computation
        System.out.println("Fibonacci with 'generate' strategy");
        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;

            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);

    }

    /**
     * Uses Streams API for files to calulate how many words are there in a file.
     */
    private static void calculateUniqueWords() {
        long uniqueWords = 0;
        try (Stream<String> lines = Files.lines(Paths.get("e:/git/personal/own/csoftz-learn-jvm-languages/app-code/java/learn-java8-9-inaction/src/main/resources/data.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
            System.out.println("Number of unique words: " + uniqueWords);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
