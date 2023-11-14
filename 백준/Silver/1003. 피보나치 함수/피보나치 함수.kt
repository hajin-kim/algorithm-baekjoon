val UNDEFINED = -1 to -1

fun fibonacciSimulation(cache: Array<Pair<Int, Int>>, n: Int): Pair<Int, Int> {
    if (cache[n] != UNDEFINED)
        return cache[n]

    val result = when (n) {
        0 -> 1 to 0
        1 -> 0 to 1

        else -> {
            val prev1 = fibonacciSimulation(cache, n - 1)
            val prev2 = fibonacciSimulation(cache, n - 2)

            prev1.first + prev2.first to prev1.second + prev2.second
        }
    }

    cache[n] = result
    return result
}

fun precalculate(): Array<Pair<Int, Int>> {
    val result = Array(41) { UNDEFINED }
    fibonacciSimulation(result, 40)
    return result
}

fun main() {
    val precalculated = precalculate()
    val t = readln().toInt()
    repeat(t) {
        val n = readln().toInt()
        val (zeros, ones) = precalculated[n]
        println("$zeros $ones")
    }
}