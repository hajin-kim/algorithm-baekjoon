fun solve(k: Int, sortedCoins: Array<Int>): Int {
    @Suppress("NAME_SHADOWING")
    var k = k
    var result = 0

    for (coin in sortedCoins.reversed()) {
        val usedCoins = k / coin
        k -= coin * usedCoins
        result += usedCoins
    }

    return result
}

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }

    val sortedCoins = Array(n) {
        readln().toInt()
    }

    val result = solve(k, sortedCoins)

    println(result)
}