const val MOD = 1000000000
fun solve(n: Int, k: Int): Int {
    val cache = Array(k) { IntArray(n + 1) }

    for (i in 0..n) {
        cache[0][i] = 1
    }

    for (length in 0 until k - 1) {
        for (chosen in 0..n) {
            for (currentSum in 0..n) {
                if (currentSum + chosen <= n)
                    cache[length + 1][currentSum + chosen] =
                        (cache[length + 1][currentSum + chosen] + cache[length][currentSum]) % MOD
            }
        }
    }

    return cache[k - 1][n]
}

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }

    val result = solve(n, k)

    println(result)
}