import java.util.ArrayDeque
import java.util.Queue

/**
 * choose `r` elements from `(n - 1) downTo 0`
 */
fun combination(
    n: Int,
    r: Int,
    permuted: List<Int> = listOf(),
    mutableResult: MutableList<List<Int>> = mutableListOf(),
): MutableList<List<Int>> {
    if (permuted.size == r) {
        mutableResult.add(permuted)
        return mutableResult
    }

    val next = n - 1
    if (next == -1)
        return mutableResult

    combination(next, r, permuted, mutableResult)
    combination(next, r, permuted + next, mutableResult)
    return mutableResult
}

fun findTargetByBfs(archer: Pair<Int, Int>, m: Int, grid: Array<BooleanArray>, d: Int): Pair<Int, Int>? {
    fun Pair<Int, Int>.isInRange(): Boolean =
        this.first in 0 until archer.first && this.second in 0 until m

    val queue: Queue<Pair<Int, Int>> = ArrayDeque()
    val up = (archer.first - 1) to archer.second
    queue += up

    var distance = 1
    while (distance <= d && queue.isNotEmpty()) {
        repeat(queue.size) {
            val now = queue.poll()
            val (r, c) = now

            if (grid[r][c])
                return now

            val neighbors = listOf(
                r to c - 1,
                r - 1 to c,
                r to c + 1,
            )
            for (neighbor in neighbors) {
                if (neighbor.isInRange())
                    queue += neighbor
            }
        }
        ++distance
    }

    return null
}

fun solve(n: Int, m: Int, grid: Array<BooleanArray>, d: Int): Int {
    val cases = combination(m, 3)

    var result = 0

    for (case in cases) {
        val allEliminated = mutableSetOf<Pair<Int, Int>>()

        repeat(n) { time ->
            val archerRow = n - time
            val archers = case.map { archerRow to it }
            val targets = archers.mapNotNull {
                findTargetByBfs(archer = it, m = m, grid = grid, d = d)
            }

            for ((r, c) in targets)
                grid[r][c] = false
            allEliminated += targets
        }

        result = maxOf(result, allEliminated.size)

        for ((r, c) in allEliminated) {
            grid[r][c] = true
        }
    }

    return result
}

fun main() {
    val (n, m, d) = readln().split(" ").map { it.toInt() }
    val grid = Array(n) {
        readln().split(" ").map { it == "1" }.toBooleanArray()
    }

    val result = solve(n, m, grid, d)

    println(result)
}
