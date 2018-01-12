package com.csoftz.study.kotlin.hello

fun printMsg(msg : String, again: String) {
    println(msg + again);
}

fun printMsg(msg: String) {
    println(msg);
}

fun main(args: Array<String>) {
    println("Hello World!")
    printMsg("The Long fox jumps the fence")
    printMsg("First and ", "Second")

    val name = if (args.size > 0) args[0] else "Kotlin"
    println("Hello, $name!")

    println("Hello, ${if (args.size > 0) args[0] else "someone"}!")
}