package com.csoftz.study.java8;

import com.csoftz.study.java8.domain.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class StreamsAPI {
    private static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));

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
        extractCartesianProduct();

    }

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

    private static void extractUniqueCharacters() {
        List<String> words = Arrays.asList("Hello", "World");
        List<String> uniqueCharacters = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());
        System.out.println(uniqueCharacters);
    }

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

    private static void getDishNamesAndLengths() {
        List<Integer> dishNames = menu.stream()
                .map(Dish::getName)
                .map(String::length)
                .collect(toList());
        dishNames.forEach(System.out::println);
    }

    private static void skipDishes() {
        List<Dish> dishes = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(toList());
        dishes.forEach(System.out::println);
    }

    private static void limitDishes() {
        List<Dish> dishes = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .limit(3)
                .collect(toList());
        dishes.forEach(System.out::println);
    }

    private static void getDishNameGreaterCalories() {
        List<Dish> specialMenu = Arrays.asList(
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER));
        specialMenu.stream()
                .filter(dish -> dish.getCalories() > 320)
                .map(d -> d.getName())
                .collect(toList())
                .forEach(System.out::println);
    }

    private static void getDistinctNumbers() {
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);
    }

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

    private static void combineStrings() {
        List<String> title = Arrays.asList("Java8", "in", "Action");
        Stream<String> s = title.stream();
        s.forEach(System.out::println);
        //s.forEach(System.out::println);

        System.out.println(title.stream().collect(joining(",")));
    }

    private static void getThreeHighCalories() {
        List<String> threeHighCaloricDishNames =
                menu.stream()
                        .filter(dish -> dish.getCalories() > 300)
                        .map(Dish::getName)
                        .limit(3)
                        .collect(toList());
        System.out.println(threeHighCaloricDishNames);
    }

    private static void getDishesType() {
        Map<Dish.Type, List<Dish>> dishesByType =
                menu.stream().collect(groupingBy(Dish::getType));
        System.out.println("DISHES TYPE");
        System.out.println(dishesByType);
        System.out.println();
    }

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

    private static String formatInts(int[] p) {
        return String.format("(%s)", Arrays.stream(p)
                .mapToObj(n -> String.valueOf(n))
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