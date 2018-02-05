/*----------------------------------------------------------------------------*/
/* Source File:   DISH.JAVA                                                   */
/* Description:   Domain object to represent a DISH                           */
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
package com.csoftz.study.java8.domain;

/**
 * Domain object to represent a DISH
 *
 * @author Carlos Adolfo Ortiz Quirós (COQ)
 * @version 1.1, Jan.22/2018
 * @since 1.8 (JDK), Jan.12/2018
 */
public class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    /**
     * Constructor with parameters
     *
     * @param name       Description of the Dish
     * @param vegetarian Indicates that the dish is  vegetarian
     * @param calories   How many calories contains
     * @param type       Indicates the kind of dish
     */
    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", vegetarian=" + vegetarian +
                ", calories=" + calories +
                ", type=" + type +
                '}';
    }

    public enum Type {MEAT, FISH, OTHER}
}