/*----------------------------------------------------------------------------*/
/* Source File:   TRADERTRANSACTIONSAPI.JAVA                                  */
/* Description:   Streams API example with the trader, transaction domain     */
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
package com.csoftz.study.java8;

import com.csoftz.study.java8.domain.Trader;
import com.csoftz.study.java8.domain.Transaction;

import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;

/**
 * Streams API example with the trader, transaction domain
 *
 * @author Carlos Adolfo Ortiz Quirós (COQ)
 * @version 1.1, Jan.22/2018
 * @since 1.8 (JDK), Jan.16/2018
 */
public class TraderTransactionsAPI extends StreamAPIBase {

    /**
     * Program entry point.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        findYear2011TransactionsAndSort();
        findTradersUniqueCities();
        findCambrigeTraderSortByName();
        findAllTradersNamesSortedAlphabetically();
        findAnyTradersInMilan();
        findTransactionValueFromCambridgeTraders();
        findHighestValueAllTransactions();
        findLowestValueAllTransactions();
    }

    /**
     * Find the transaction with the smallest value.
     */
    private static void findLowestValueAllTransactions() {
        System.out.println("findLowestValueAllTransactions");
        Optional<Integer> smallestTransaction =
                transactions.stream()
                        .map(Transaction::getValue)
                        .reduce((t1, t2) ->
                                (t1 <= t2) ? t1 : t2);
        System.out.println(smallestTransaction);
        System.out.println("Another way to do MIN");
        System.out.println(transactions.stream()
                .min(comparing(Transaction::getValue))
        );
        System.out.println(transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min)
        );
        System.out.println(transactions.stream()
                .map(Transaction::getValue)
                .min((t1, t2) -> Integer.compare(t1, t2))
        );
    }

    /**
     * What’s the highest value of all the transactions?
     */
    private static void findHighestValueAllTransactions() {
        System.out.println("findHighestValueAllTransactions");
        Optional<Integer> highestValue =
                transactions.stream()
                        .map(Transaction::getValue)
                        .reduce(Integer::max);
        System.out.println(highestValue);
        System.out.println("Another way to do MAX");
        System.out.println(
                transactions.stream()
                        .map(Transaction::getValue)
                        .reduce((t1, t2) ->
                                (t1 >= t2) ? t1 : t2)
        );
        System.out.println(transactions.stream()
                .map(Transaction::getValue)
                .max((t1, t2) -> Integer.compare(t1, t2))

        );
    }

    /**
     * Print all transactions’ values from the traders living in Cambridge.
     */
    private static void findTransactionValueFromCambridgeTraders() {
        System.out.println("findTransactionValueFromCambridgeTraders");
        transactions.stream()
                .filter(t -> "Cambridge".equals(t.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);
    }

    /**
     * Are any traders based in Milan?
     */
    private static void findAnyTradersInMilan() {
        System.out.println("findAnyTradersInMilan");
        boolean milanBased = transactions.stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Milan"));
        System.out.printf("milanBased: %s%n", milanBased);
    }

    /**
     * Return a string of all traders’ names sorted alphabetically
     */
    private static void findAllTradersNamesSortedAlphabetically() {
        System.out.println("findAllTradersNamesSortedAlphabetically");
        String traderStr =
                transactions.stream()
                        .map(transaction -> transaction.getTrader().getName())
                        .distinct()
                        .sorted()
                        .reduce("", (n1, n2) -> n1 + n2);
        System.out.println(traderStr); // This solution is not efficient as it concatenates strings for every iteration.
        System.out.println("A more efficient way:");
        System.out.println(transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .collect(joining(",")) // It is efficient as it uses an StringBuilder internally.
        );
    }

    /**
     * Find all traders from Cambridge and sort them by name.
     */
    private static void findCambrigeTraderSortByName() {
        System.out.println("findCambrigeTraderSortByName");
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> t.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .forEach(System.out::println);
    }

    /**
     * What are all the unique cities where the traders work?
     */
    private static void findTradersUniqueCities() {
        System.out.println("findTradersUniqueCities");
        transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * Find all transactions in the year 2011 and sort them by value (small to high).
     */
    private static void findYear2011TransactionsAndSort() {
        System.out.println("findYear2011TransactionsAndSort");
        transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .forEach(System.out::println);
    }
}
