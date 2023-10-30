const val AIR = 0
const val CHEESE = 1

fun solve(n: Int, grid: Array<IntArray>): Int {
    fun isInRange(r: Int, c: Int): Boolean = r in 0 until n && c in 0 until n

    val scores = Array(n) { IntArray(n) }
    var maxScore = 0

    grid.flatMapIndexed { r, row ->
        row.mapIndexed { c, value -> Triple(value, r, c) }
    }
        .sortedByDescending { it.first }
        .forEach { (value, r, c) ->
            var nearMaxScore = 0
            listOf(
                r + 1 to c,
                r - 1 to c,
                r to c + 1,
                r to c - 1,
            ).forEach { (nr, nc) ->
                if (isInRange(nr, nc) && grid[nr][nc] > value) // for the case that score equals
                    nearMaxScore = maxOf(nearMaxScore, scores[nr][nc])
            }

            val score = nearMaxScore + 1
            scores[r][c] = score
            maxScore = maxOf(score, maxScore)
        }

    return maxScore
}

fun main() {
    val (n) = readln().split(" ").map { it.toInt() }
    val grid = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }

    val result = solve(n, grid)

    println(result)
}