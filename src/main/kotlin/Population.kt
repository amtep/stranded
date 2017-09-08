package net.clueonic.stranded

/**
  Demographic represents a group of people that can be treated identically.

  @property age is in years. Everyone is treated as having the same birthday.
  @property childBearing represents having the right organs for getting pregnant.
  The size of a demographic is tracked separately by the caller.
*/
data class Demographic(val age: Int, val childBearing: Boolean) {
    companion object {
        fun randomSurvivor() = Demographic(
            age = randomSurvivorAge(),
            childBearing = chance(0.5)
        )

        // Survivors of a spaceship crash are not distributed like
        // normal planetary populations would be.
        fun randomSurvivorAge() = when(rand()) {
            in 0.00 .. 0.40 -> dieroll(10, 30) + dieroll(10, 20)  // crew
            in 0.40 .. 0.45 -> dieroll(30, 75)  // officers
            in 0.45 .. 0.65 -> dieroll(20, 55) + dieroll(10, 20)  // business
            in 0.65 .. 0.75 -> dieroll(15, 25)  // students etc
            // families, split into three cases to allow somewhat
            // gradual dropoff of age of eldest relative
            in 0.75 .. 0.85 -> dieroll(0, 80)
            in 0.85 .. 0.95 -> dieroll(0, 90)
            else -> dieroll(0, 99)
        }
    }
}

class Population() {
    // Maps each demographic to its size in the population
    private val cohorts = HashMap<Demographic, Int>(1000)

    private fun addPopulation(dem: Demographic, size: Int) {
        assert(size >= 0)
        if (size == 0)
            return

        val oldSize = cohorts.get(dem) ?: 0
        cohorts.put(dem, oldSize + size)
    }

    fun generateSurvivors(n: Int) {
        for (i in 1..n) {
            addPopulation(Demographic.randomSurvivor(), 1)
        }
    }

    fun count() = cohorts.values.sum()

    fun describeLong(): String {
        val total = count()
        val children = cohorts.asIterable().sumBy { (dem, size) -> if (dem.age < 15) size else 0 }
        val adults = total - children

        return "Population: ${total} (${children} children, ${adults} adults)"
    }

    fun ageOneYear() {
        val newCohorts = cohorts.flatMap { (dem, size) ->
            listOf (
                Pair(
                    Demographic(dem.age + 1, dem.childBearing),
                    size - binomialChance(naturalDeathP(dem.age), size)
                )
            )
        }
        cohorts.clear()
        for ((dem, size) in newCohorts) {
            addPopulation(dem, size)
        }
    }

    fun naturalDeathP(age: Int) = when (age) {
        0 -> 0.005
        in 1..5 -> 0.0003
        in 6..12 -> 0.0001
        in 13..18 -> 0.0004
        in 19..30 -> 0.001
        in 31..42 -> 0.002
        in 43..46 -> 0.003
        in 47..49 -> 0.004
        in 50..55 -> 0.006
        in 56..64 -> 0.01
        in 65..70 -> 0.02
        in 71..75 -> 0.03
        in 76..80 -> 0.05
        in 81..85 -> 0.07
        in 86..90 -> 0.13
        in 91..95 -> 0.2
        in 96..99 -> 0.3
        in 100..110 -> 0.4
        else -> 0.5
    }
}
