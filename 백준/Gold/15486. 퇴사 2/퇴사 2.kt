fun solve(n: Int, ts: IntArray, ps: IntArray): Int {
    val cache = IntArray(n + 50)
    var result = 0

    for (i in 0 until n) {
        val next = i + ts[i] - 1

        val prevMax = ((i - 50).coerceAtLeast(0) until i)
            .maxOfOrNull { cache[it] } ?: 0

        cache[next] = maxOf(cache[next], prevMax + ps[i])
        result = maxOf(result, cache[i])
    }
    return result
}

fun main() {
    val n = readln().toInt()
    val (ts, ps) = List(n) {
        readln().split(" ").map { it.toInt() }.let { it[0] to it[1] }
    }
        .unzip()
        .let { (ts, ps) -> ts.toIntArray() to ps.toIntArray() }

    val result = solve(n, ts, ps)

    println(result)
}