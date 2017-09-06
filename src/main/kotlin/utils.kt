package net.clueonic.stranded

import java.util.Random

val RAND = Random()

fun chance(probability: Double): Boolean = RAND.nextDouble() < probability

fun dieroll(min: Int, max: Int): Int = RAND.nextInt(max - min + 1) + min

fun rand(): Double = RAND.nextDouble()
