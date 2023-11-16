const val NIL = 0
const val MOD = 10007

fun tile(n: Int, cache: IntArray): Int {
    if (n == 1) return 1
    if (n == 2) return 3
    if (cache[n] != NIL) return cache[n]

    val result = (tile(n - 1, cache) + tile(n - 2, cache) * 2) % MOD
    cache[n] = result

    return result
}

fun solve(n: Int): Int {
    val cache = IntArray(n + 1)
    return tile(n, cache)
}

fun main() {
    val n = readln().toInt()

    val result = solve(n)

    println(result)
}