fun main(args: Array<String>) {
    val n = readln().toInt()

    // |sizes| = n + 1
    val sizes = (1 until n)
        .map { readln().split(" ").first().toInt() }
        .plus(readln().split(" ").map { it.toInt() })
        .toMutableList()
        .toIntArray()

    val cache = Array(n + 1) { IntArray(n + 1) }

    fun dp(left: Int, right: Int): Int {
        if (left + 2 > right)
            return 0

        val cached = cache[left][right]
        if (cached != 0)
            return cached

        if (left + 2 == right) {
            val result = sizes[left] * sizes[left + 1] * sizes[left + 2]
            cache[left][right] = result
            return result
        }

        val splitPoints = (left + 1 until right)
        val result = splitPoints.minOf {
            dp(left, it) + dp(it, right) + sizes[left] * sizes[it] * sizes[right]
        }

        cache[left][right] = result
        return result
    }

    val result = dp(0, n)

    println(result)
}
