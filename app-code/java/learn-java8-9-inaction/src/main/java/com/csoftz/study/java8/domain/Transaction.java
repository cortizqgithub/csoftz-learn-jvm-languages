/*----------------------------------------------------------------------------*/
/* Source File:   TRANSACTION.JAVA                                            */
/* Description:   Domain object to represent a TRANSACTION                    */
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
 * Domain object to represent a TRANSCATION
 *
 * @author Carlos Adolfo Ortiz Quirós (COQ)
 * @version 1.1, Jan.22/2018
 * @since 1.8 (JDK), Jan.16/2018
 */
public class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;

    /**
     * Constructor with parameters
     *
     * @param trader Associated trader who made the transaction
     * @param year   Transaction year
     * @param value  Value traded.
     */
    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public Trader getTrader() {
        return trader;
    }

    public int getYear() {
        return year;
    }

    public int getValue() {
        return value;
    }

    /**
     * Prints domain object as an String representation.
     *
     * @return
     */
    @Override
    public String toString() {
        return "Transaction{" +
                "trader=" + trader +
                ", year=" + year +
                ", value=" + value +
                '}';
    }
}
