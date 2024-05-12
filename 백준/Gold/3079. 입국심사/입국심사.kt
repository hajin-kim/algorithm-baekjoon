fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val ts = Array(n) { readln().toInt() }

    val result = solve(n, m, ts)

    println(result)
}

fun solve(n: Int, m: Int, ts: Array<Int>): Long {
    return leftmostParametricSearch(m, ts)
}

// https://en.wikipedia.org/wiki/Binary_search_algorithm
fun leftmostParametricSearch(m: Int, ts: Array<Int>): Long {
    var left = 1L
    var right = m.toLong() * ts.max()

    while (left < right) {
        // floor
        val mid = (left + right) / 2

        var value = 0L
        for (t in ts) {
            value += mid / t
            if (value < 0) {
                value = Long.MAX_VALUE
                break
            }
        }

        if (value < m)
            left = mid + 1
        else
            right = mid
    }

    return left
}
