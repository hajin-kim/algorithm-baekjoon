import java.util.*

const val AIR = 0
const val CHEESE = 1

fun solve(n: Int, m: Int, grid: Array<IntArray>): Int {
    var nCheeses = grid.sumOf { row -> row.count { it == CHEESE } }
    var elapsedTime = 0

    fun isInRange(r: Int, c: Int): Boolean = r in 0 until n && c in 0 until m

    while (nCheeses > 0) {
        val discovered = Array(n) { BooleanArray(m) }
        val queue: Queue<Pair<Int, Int>> = LinkedList()

        discovered[0][0] = true
        queue.add(0 to 0)

        while (queue.isNotEmpty()) {
            val (r, c) = queue.poll()

            listOf(
                r to c + 1,
                r to c - 1,
                r + 1 to c,
                r - 1 to c,
            ).forEach { next ->
                val (nr, nc) = next

                if (!isInRange(nr, nc))
                    return@forEach

                if (discovered[nr][nc]) {
                    if (grid[nr][nc] == CHEESE) {
                        grid[nr][nc] = AIR
                        --nCheeses
                    }

                    return@forEach
                }

                discovered[nr][nc] = true

                if (grid[nr][nc] == AIR)
                    queue.add(next)
            }
        }

        ++elapsedTime
    }

    return elapsedTime
}

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val grid = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }

    val result = solve(n, m, grid)

    println(result)
}