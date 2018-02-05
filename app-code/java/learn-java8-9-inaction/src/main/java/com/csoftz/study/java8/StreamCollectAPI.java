/*----------------------------------------------------------------------------*/
/* Source File:   STREAMCOLLECTAPI.JAVA                                       */
/* Description:   Java 8 Stream Collection API learning (use of Collectors)   */
/* Author:        Carlos Adolfo Ortiz Quirós (COQ)                            */
/* Date:          Jan.18/2018                                                 */
/* Last Modified: Feb.05/2018                                                 */
/* Version:       1.3                                                         */
/* Copyright (c), 2018 CSoftZ.                                                */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Jan.12/2018 COQ  File created.
 -----------------------------------------------------------------------------*/
package com.csoftz.study.java8;

import com.csoftz.study.java8.domain.Dish;

import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toList;

/**
 * Java 8 Stream Collection API learning (use of Collectors)
 *
 * @author Carlos Adolfo Ortiz Quirós (COQ)
 * @version 1.3, Feb.05/2018
 * @since 1.8 (JDK), Jan.18/2018
 */
@SuppressWarnings("unused")
public class StreamCollectAPI extends StreamAPIBase {
    /**
     * Program entry point.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        //countMenuEntries();
        //getMaxCaloriesInMenu();
        //doSummarization();
        //doStringJoining();
        //doReducing();
        doGroupingBy();
    }

    /**
     * Sample about groupingBy collector
     */
    private static void doGroupingBy() {
        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType));
        System.out.println("Dishes By Type");
        System.out.println(dishesByType);

        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(
                groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }));
        System.out.println("Dishes By Caloric Level");
        System.out.println(dishesByCaloricLevel);

        // This only works for Java 9 (http://www.baeldung.com/java9-stream-collectors)
        // There is a workaround for Java 8
        // https://stackoverflow.com/questions/33001883/java8-is-there-a-filtering-collector
        //Map<Dish.Type, List<Dish>> caloricDishesByType = menu.stream()
        //        .collect(groupingBy(Dish::getType, filtering(dish -> dish.getCalories() > 500, toList())));
        //System.out.println("Caloric Dishes By Type");
        //System.out.println(caloricDishesByType);

        Map<Dish.Type, List<String>> dishNamesByType = menu.stream()
                .collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));
        System.out.println("Dish Names By Type");
        System.out.println(dishNamesByType);

        System.out.println("Use of FlatMap");
        Map<String, List<String>> dishTags = new HashMap<>();
        dishTags.put("pork", asList("greasy", "salty"));
        dishTags.put("beef", asList("salty", "roasted"));
        dishTags.put("chicken", asList("fried", "crisp"));
        dishTags.put("french fries", asList("greasy", "fried"));
        dishTags.put("rice", asList("light", "natural"));
        dishTags.put("season fruit", asList("fresh", "natural"));
        dishTags.put("pizza", asList("tasty", "salty"));
        dishTags.put("prawns", asList("tasty", "roasted"));
        dishTags.put("salmon", asList("delicious", "fresh"));

        // The following flatMapping is only for Java 9
        // See http://www.baeldung.com/java9-stream-collectors
        //Map<Dish.Type, Set<String>> dishNamesByType = menu.stream()
        //        .collect(groupingBy(Dish::getType,
        //                flatMapping(dish -> dishTags.get( dish.getName() ).stream(),
        //                        toSet())));
        //System.out.println(dishNamesByType);

    }

    /**
     * Play with 'reducing' Collector factory.
     */
    private static void doReducing() {
        // A similar way to do so is found in 'doSummarization()'.
        int totalCalories = menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
        System.out.println(totalCalories);

        int totalCalories2 = menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
        System.out.println(totalCalories2);

        // A similar way to do so is found in 'getMaxCaloriesInMenu()'
        Optional<Dish> mostCalorieDish =
                menu.stream().collect(reducing(
                        (d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
        System.out.println(mostCalorieDish);
    }

    /**
     * Play with the 'joining' Collector
     */
    private static void doStringJoining() {
        //menu.stream().collect(joining()); -> wonder why this does not compile
        System.out.println(menu.stream().map(Dish::getName).collect(joining()));
        System.out.println(menu.stream().map(Dish::getName).collect(joining(",")));
        System.out.println(menu.stream().map(Dish::toString).collect(joining()));

        // Using 'reducing'.
        System.out.println(menu.stream().map(Dish::getName).collect(reducing((s1, s2) -> s1 + s2)).get());
        System.out.println(menu.stream().collect(reducing("", Dish::getName, (s1, s2) -> s1 + s2)));
    }

    /**
     * Samples for Summarization.
     */
    private static void doSummarization() {
        // Here you can also use 'summingLong' and 'summingDouble' collector methods.
        int totalCalories = menu.stream()
                .collect(summingInt(Dish::getCalories));
        System.out.println(totalCalories);

        double avgCalories = menu.stream().collect(averagingInt(Dish::getCalories));
        System.out.println(avgCalories);

        // You can also use 'summarizingLong' and 'summarizingDouble' collector methods.
        IntSummaryStatistics menuStatistics =
                menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics);
    }

    /**
     * Determines which entry in menu
     */
    private static void getMaxCaloriesInMenu() {
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> mostCalorieDish = menu.stream().collect(maxBy(dishCaloriesComparator));
        System.out.println(mostCalorieDish);
    }

    /**
     * Count the number of entries in menu list.
     */
    private static void countMenuEntries() {
        long howManyDishes = menu.stream().collect(counting());
        System.out.println(howManyDishes);
    }

    enum CaloricLevel {DIET, NORMAL, FAT}
}
