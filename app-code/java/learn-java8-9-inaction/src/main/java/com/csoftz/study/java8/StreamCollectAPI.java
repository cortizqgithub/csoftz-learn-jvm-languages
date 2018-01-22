/*----------------------------------------------------------------------------*/
/* Source File:   STREAMCOLLECTAPI.JAVA                                       */
/* Description:   Java 8 Stream Collection API learning (use of Collectors)   */
/* Author:        Carlos Adolfo Ortiz Quirós (COQ)                            */
/* Date:          Jan.18/2018                                                 */
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

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Optional;

import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;

/**
 * Java 8 Stream Collection API learning (use of Collectors)
 *
 * @author Carlos Adolfo Ortiz Quirós (COQ)
 * @version 1.1, Jan.22/2018
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
        doSummarization();
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
}
