fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    // diffMap[y][x] = x to y
    val diffMap = (1..n).map {
        readln().map {
            when (it) {
                'L' -> -1 to 0
                'R' -> 1 to 0
                'U' -> 0 to -1
                'D' -> 0 to 1
                else -> 0 to 0
            }
        }
            .toTypedArray()
    }
        .toTypedArray()

    val visitedAt = Array(n) { IntArray(m) }
    var now = 1
    var result = 0

    tailrec fun isNewCycle(y: Int, x: Int): Boolean {
        if (visitedAt[y][x] == now)
            return true
        if (visitedAt[y][x] != 0)
            return false

        visitedAt[y][x] = now
        val (dx, dy) = diffMap[y][x]

        return isNewCycle(y + dy, x + dx)
    }

    (0 until n).forEach { y ->
        (0 until m).forEach { x ->
            if (isNewCycle(y, x))
                ++result
            ++now
        }
    }

    println(result)
}