/*----------------------------------------------------------------------------*/
/* Source File:   TRADER.JAVA                                                 */
/* Description:   Domain object to represent a TRADER                         */
/* Author:        Carlos Adolfo Ortiz Quirós (COQ)                            */
/* Date:          Jan.16/2018                                                 */
/* Last Modified: Jan.22/2018                                                 */
/* Version:       1.1                                                         */
/* Copyright (c), 2018 CSoftZ.                                                */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Jan.16/2018 COQ  File created.
 -----------------------------------------------------------------------------*/
package com.csoftz.study.java8.domain;

/**
 * Domain object to represent a TRADER
 *
 * @author Carlos Adolfo Ortiz Quirós (COQ)
 * @version 1.1, Jan.22/2018
 * @since 1.8 (JDK), Jan.16/2018
 */
public class Trader {
    private final String name;
    private final String city;

    /**
     * Constructor with parameters
     *
     * @param n name of the Trader
     * @param c Location of the Trader
     */
    public Trader(String n, String c) {
        this.name = n;
        this.city = c;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
