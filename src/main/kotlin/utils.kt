package net.clueonic.stranded

import java.util.Random

val RAND = Random()

fun chance(probability: Double): Boolean = RAND.nextDouble() < probability

fun dieroll(min: Int, max: Int): Int = RAND.nextInt(max - min + 1) + min

fun rand(): Double = RAND.nextDouble()

fun binomialChance(p: Double, size: Int): Int {
    if (p * size >= 9 && (1 - p) * size >= 9) {
        // size is large enough to be represented with normal distribution
        val result = (RAND.nextGaussian() * p * size).toInt()
        return when {
            result < 0 -> 0
            result > size -> size
            else -> result
        }
    } else {
        // brute force
        var result = 0
        for (i in 1..size) {
            if (chance(p))
                ++result
        }
        return result
    }
}
