package com.csoftz.study.kotlin

fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz($i) "
    i % 3 == 0 -> "Fizz($i) "
    i % 5 == 0 -> "Buzz($i) "
    else -> "$i "
}

fun main(args: Array<String>) {
    val oneToTen = 1..10
    for (i in 1..100) {
        print(fizzBuzz(i))
    }
    println()
    for (i in 100 downTo 1 step 2) {
        print(fizzBuzz(i))
    }
    println()
    for (i in 9 downTo 1 step 3) {
        print("$i ")
    }
    println()
    for (i in 1 until 10) {
        print("$i ")
    }
}