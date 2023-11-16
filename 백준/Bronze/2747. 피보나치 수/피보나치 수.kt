fun fibonacci(n: Int, cache: IntArray): Int {
    if (n <= 1) return n
    if (cache[n] != 0) return cache[n]

    val result = fibonacci(n - 1, cache) + fibonacci(n - 2, cache)
    cache[n] = result

    return result
}

fun solve(n: Int): Int {
    val cache = IntArray(n + 1)
    return fibonacci(n, cache)
}

fun main() {
    val n = readln().toInt()

    val result = solve(n)

    println(result)
}