/*----------------------------------------------------------------------------*/
/* Source File:   STREAMSAPI.JAVA                                             */
/* Description:   Java 8 Stream Collection API learning                       */
/* Author:        Carlos Adolfo Ortiz Quirós (COQ)                            */
/* Date:          Jan.12/2018                                                 */
/* Last Modified: Jan.22/2018                                                 */
/* Version:       1.1                                                         */
/* Copyright (c), 2018 CSoftZ.                                                */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Jan.12/2018 COQ  File created.
 -----------------------------------------------------------------------------*/
package com.csoftz.study.java8;

import com.csoftz.study.java8.domain.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * Java 8 Stream Collection API learning
 *
 * @author Carlos Adolfo Ortiz Quirós (COQ)
 * @version 1.1, Jan.22/2018
 * @since 1.8 (JDK), Jan.12/2018
 */
@SuppressWarnings("unused")
public class StreamsAPI extends StreamAPIBase {

    /**
     * Program entry point.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        //getSampleLowCaloriesDishes();
        //getDishesType();
        //getThreeHighCalories();
        //combineStrings();
        //getDishNameDebug();
        //getDistinctNumbers();
        //getDishNameGreaterCalories();
        //limitDishes();
        //skipDishes();
        //getDishNames();
        //getWordLengths();
        //getDishNamesAndLengths();
        //extractUniqueCharacters();
        //extractCartesianProduct();
        //findData();
        //reduceValues();
        //generateStreams();

        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();
        System.out.println(sum);
    }

    /**
     * How to generate Streams from values and other goodies.
     */
    private static void generateStreams() {
        Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);
        //  Stream.ofNullable(value) --> Only in Java 9
    }

    /**
     * redude Stream API examples
     */
    private static void reduceValues() {
        List<Integer> numbers = Arrays.asList(4, 5, 3, 9);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b); // Integer::sum instead of lambda
        System.out.println(sum);

        int product = numbers.stream().reduce(1, (a, b) -> a * b);
        System.out.println(product);

        Optional<Integer> sumOptional = numbers.stream().reduce((a, b) -> a + b);
        System.out.println(sumOptional);

        System.out.println("MAX (method ref) " + numbers.stream().reduce(Integer::max));
        System.out.println("MAX (lambda) " + numbers.stream().reduce((x, y) -> (x >= y) ? x : y));
        System.out.println("MIN (method ref) " + numbers.stream().reduce(Integer::min));
        System.out.println("MIN (lambda) " + numbers.stream().reduce((x, y) -> (x <= y) ? x : y));

        // How would you count the number of dishes in a stream using the 'map' and 'reduce' methods?
        int count = menu.stream()
                .map(d -> 1)
                .reduce(0, (a, b) -> a + b); // This uses the map-reduce pattern

        System.out.println("Number of dishes: (map-reduce version): " + count);
        System.out.println("Number of dishes: (count version): " + menu.stream().count());
        System.out.println("Number of dishes: (LIST count): " + menu.size());
    }

    /**
     * Data finding Stream API examples
     */
    private static void findData() {
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }
        boolean isHealthy = menu.stream()
                .allMatch(dish -> dish.getCalories() < 1000);
        System.out.printf("Is all Healthy %s:%n", isHealthy);
        boolean isNoneHealthy = menu.stream()
                .noneMatch(d -> d.getCalories() >= 1000);
        System.out.printf("Is none Health %s\n", isNoneHealthy);
        Optional<Dish> anyDish =
                menu.stream()
                        .filter(Dish::isVegetarian)
                        .findAny();
        System.out.printf("Any dish: %s%n", anyDish);

        menu.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(dish -> System.out.println(dish.getName()));

        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        someNumbers.stream()
                .map(n -> n * n)
                .filter(n -> n % 3 == 0)
                .forEach(n -> System.out.print(n + " "));

        Optional<Integer> firstSquareDivisibleByThree = someNumbers.stream()
                .map(n -> n * n)
                .filter(n -> n % 3 == 0)
                .findFirst();
        System.out.println(firstSquareDivisibleByThree);
    }

    /**
     * Stream API examples for cartesian product
     */
    private static void extractCartesianProduct() {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(3, 4);
        List<int[]> pairs = numbers1.stream()
                .flatMap(i -> numbers2.stream()
                        .map(j -> new int[]{i, j}))
                .collect(toList());
        pairs.stream()
                .map(StreamsAPI::formatInts)
                .forEach(System.out::print);
        System.out.println();
        System.out.println("OTHER: " + pairs.stream()
                .map(StreamsAPI::formatInts)
                .collect(joining(",")));
        System.out.println();

        List<int[]> pairs2 = numbers1.stream()
                .flatMap(i -> numbers2.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j}))
                .collect(toList());

        pairs2.stream()
                .map(StreamsAPI::formatInts)
                .forEach(System.out::print);

        System.out.println();
        System.out.println("OTHER: " + pairs2.stream()
                .map(StreamsAPI::formatInts)
                .collect(joining(",")));
    }

    /**
     * Stream API examples get unique characters.
     */
    private static void extractUniqueCharacters() {
        List<String> words = Arrays.asList("Hello", "World");
        List<String> uniqueCharacters = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        System.out.println(uniqueCharacters);
    }

    /**
     * Stream API examples get Word lengths
     */
    private static void getWordLengths() {
        List<String> words = Arrays.asList("Java8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(toList());
        wordLengths.forEach(System.out::println);
    }

    private static void getDishNames() {
        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(toList());
        dishNames.forEach(System.out::println);
    }

    /**
     * Stream API examples sample to get Dish Names and  their length
     */
    private static void getDishNamesAndLengths() {
        List<Integer> dishNames = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(toList());
        dishNames.forEach(System.out::println);
    }

    /**
     * Stream API examples Skip example
     */
    private static void skipDishes() {
        List<Dish> dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(toList());
        dishes.forEach(System.out::println);
    }

    /**
     * Stream API examples get limit
     */
    private static void limitDishes() {
        List<Dish> dishes = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .limit(3)
                .collect(toList());
        dishes.forEach(System.out::println);
    }

    /**
     * Stream API examples: Retrieves all dishes with higher calories.
     */
    private static void getDishNameGreaterCalories() {
        List<Dish> specialMenu = Arrays.asList(
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER));
        specialMenu.stream()
                .filter(dish -> dish.getCalories() > 320)
                .map(Dish::getName)
                .collect(toList())
                .forEach(System.out::println);
    }

    /**
     * Stream API examples: Get only distinct numbers
     */
    private static void getDistinctNumbers() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * Stream API examples: Print lines to console to indicate Stream API execution.
     */
    private static void getDishNameDebug() {
        List<String> names =
                menu.stream().filter(dish -> {
                    System.out.println("filtering " + dish.getName());
                    return dish.getCalories() > 300;
                }).map(dish -> {
                    System.out.println("mapping " + dish.getName());
                    return dish.getName();
                }).limit(3).collect(toList());
        System.out.println(names);
    }

    /**
     * Stream API examples: A way to combine strings (joining)
     */
    private static void combineStrings() {
        List<String> title = Arrays.asList("Java8", "in", "Action");
        Stream<String> s = title.stream();
        s.forEach(System.out::println);
        //s.forEach(System.out::println);

        System.out.println(title.stream().collect(joining(",")));
    }

    /**
     * Stream API examples: Get the three highest calorie dishes
     */
    private static void getThreeHighCalories() {
        List<String> threeHighCaloricDishNames =
                menu.stream()
                        .filter(dish -> dish.getCalories() > 300)
                        .map(Dish::getName)
                        .limit(3)
                        .collect(toList());
        System.out.println(threeHighCaloricDishNames);
    }

    /**
     * Stream API examples:Get all dishes grouped by dishes
     */
    private static void getDishesType() {
        Map<Dish.Type, List<Dish>> dishesByType =
                menu.stream().collect(groupingBy(Dish::getType));
        System.out.println("DISHES TYPE");
        System.out.println(dishesByType);
        System.out.println();
    }

    /**
     * Stream API examples: Get the lowest calories for dishes
     */
    private static void getSampleLowCaloriesDishes() {
        List<String> lowCaloricDishesName = menu.parallelStream()
                .filter(d -> d.getCalories() < 400)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(toList());

        System.out.println("GET LOW CALORIES DISHES");
        lowCaloricDishesName.forEach(System.out::println);
        System.out.println();
    }

    /**
     * Stream API examples: A method reference used instead of a Lambda expression.
     * @param p the array of integers to format
     * @return An String with array values formatted.
     */
    private static String formatInts(int[] p) {
        return String.format("(%s)", Arrays.stream(p)
                .mapToObj(String::valueOf)
                .collect(joining(",")));
    }
}

/* this only works using Java 9
        List<Dish> filteredMenu = specialMenu.stream()
                                             .takeWhile(dish -> dish.getCalories() > 320)
                                             .collect(toList());

        List<Dish> filteredMenu = specialMenu.stream()
                                             .dropWhile(dish -> dish.getCalories() > 320)
                                             .collect(toList());
        */