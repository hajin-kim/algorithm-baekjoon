const val MOD = 10007

fun solve(n: Int): Int {
    val cache = IntArray(10) { 1 }
    repeat(n - 1) {
        var casesOfPreviousDigit = 0
        repeat(10) { i ->
            casesOfPreviousDigit += cache[i]
            casesOfPreviousDigit %= MOD
            cache[i] = casesOfPreviousDigit
        }
    }

    return cache.sum() % MOD
}

fun main() {
    val n = readln().toInt()

    val result = solve(n)

    println(result)
}