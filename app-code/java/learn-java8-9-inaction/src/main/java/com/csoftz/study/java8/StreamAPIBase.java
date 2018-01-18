/*----------------------------------------------------------------------------*/
/* Source File:   STREAMAPIBASE.JAVA                                          */
/* Description:   Abstract Streams API base (common methods and fields)       */
/* Author:        Carlos Adolfo Ortiz Quirós (COQ)                            */
/* Date:          Jan.16/2018                                                 */
/* Last Modified: Jan.16/2018                                                 */
/* Version:       1.1                                                         */
/* Copyright (c), 2017 CSoftZ, Ceiba.                                         */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Jan.16/2018 COQ  File created.
 -----------------------------------------------------------------------------*/
package com.csoftz.study.java8;

import com.csoftz.study.java8.domain.Dish;

import java.util.Arrays;
import java.util.List;

/**
 * Abstract Streams API base (common methods and fields)
 *
 * @author Carlos Adolfo Ortiz Quirós (COQ)
 * @version 1.1, Jan.16/2018
 * @since 1.8 (JDK), Jan.16/2018
 */
public abstract class StreamAPIBase {
    protected static List<Dish> menu = Arrays.asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH));
}
