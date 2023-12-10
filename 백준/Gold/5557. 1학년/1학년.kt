const val LIMIT = 20
val RANGE = 0..20

fun solve(n: Int, numbers: IntArray): Long {
    var intermediateCounts = LongArray(LIMIT + 1)
    intermediateCounts[numbers.first()] = 1

    for (i in 1 until n - 1) {
        val currentNumber = numbers[i]
        val newIntermediateCounts = LongArray(LIMIT + 1)

        for (previousIntermediate in RANGE) {
            val plus = previousIntermediate + currentNumber
            val minus = previousIntermediate - currentNumber

            listOf(plus, minus)
                .filter { it in RANGE }
                .forEach { newIntermediate ->
                    newIntermediateCounts[newIntermediate] += intermediateCounts[previousIntermediate]
                }
        }

        intermediateCounts = newIntermediateCounts
    }
    return intermediateCounts[numbers.last()]
}

fun main() {
    val n = readln().toInt()
    val numbers = readln().split(" ").map { it.toInt() }.toIntArray()

    val result = solve(n, numbers)

    println(result)
}