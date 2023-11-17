fun solve(n: Int, m: Int, candies: Array<IntArray>): Int {
    val cache = Array(n) { IntArray(m) }

    fun isInRange(r: Int, c: Int): Boolean =
        r in 0 until n && c in 0 until m

    for (r in 0 until n) {
        for (c in 0 until m) {
            val maxNeighbor = listOf(
                r - 1 to c,
                r to c - 1,
                r - 1 to c - 1,
            )
                .filter { (nr, nc) -> isInRange(nr, nc) }
                .maxOfOrNull { (nr, nc) -> cache[nr][nc] }
                ?: 0

            cache[r][c] = maxNeighbor + candies[r][c]
        }
    }

    return cache[n - 1][m - 1]
}

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val candies = Array(n) {
        readln().split(" ").map { it.toInt() }.toIntArray()
    }

    val result = solve(n, m, candies)

    println(result)
}