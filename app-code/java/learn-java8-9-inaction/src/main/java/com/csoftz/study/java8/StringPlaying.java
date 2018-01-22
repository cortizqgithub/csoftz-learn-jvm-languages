/*----------------------------------------------------------------------------*/
/* Source File:   STRINGPLAYING.JAVA                                          */
/* Description:   String playing with the streams api collection.             */
/* Author:        Carlos Adolfo Ortiz Quirós (COQ)                            */
/* Date:          Jan.22/2018                                                 */
/* Last Modified: Jan.22/2018                                                 */
/* Version:       1.1                                                         */
/* Copyright (c), 2018 CSoftZ.                                                */
/*----------------------------------------------------------------------------*/
/*-----------------------------------------------------------------------------
 History
 Jan.22/2018 COQ  File created.
 -----------------------------------------------------------------------------*/
package com.csoftz.study.java8;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * String playing with the streams api collection.
 *
 * @author Carlos Adolfo Ortiz Quirós (COQ)
 * @version 1.1, Jan.22/2018
 * @since 1.8 (JDK), Jan.16/2018
 */
public class StringPlaying {

    private static List<String> data = Arrays.asList("SocOptionImpl{label='A First Role being created', value='A First Role being created'}",
            "SocOptionImpl{label='Role 1', value='Role 1'}",
            "SocOptionImpl{label='Role 10', value='Role 10'}",
            "SocOptionImpl{label='Role Kind B 10', value='Role Kind B 10'}");

    /**
     * Program entry point.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        String roles = data.stream().map(s -> {
            String[] info = s.split("'");
            return info[1];
        }).collect(joining(","));

        System.out.println(roles);

    }
}
