import java.util.LinkedList
import java.util.Queue

const val WALL = 'X'
const val WATER = '*'
const val START = 'S'
const val END = 'D'

fun Pair<Int, Int>.isWall(grid: Array<String>): Boolean =
    grid[first][second] == WALL

fun Pair<Int, Int>.isEnd(grid: Array<String>): Boolean =
    grid[first][second] == END

fun Array<String>.listPositionsOf(char: Char): List<Pair<Int, Int>> {
    val result = mutableListOf<Pair<Int, Int>>()

    for (i in this.indices) {
        val row = this[i]
        for (j in row.indices) {
            if (row[j] == char)
                result += i to j
        }
    }

    return result
}

const val UNVISTED = 10000000

fun getInvalidAtWithBfs(r: Int, c: Int, grid: Array<String>): Array<IntArray> {
    fun Pair<Int, Int>.isInRange(): Boolean =
        first in 0 until r && second in 0 until c

    val invalidAt = Array(r) { IntArray(c) { UNVISTED } }

    val queue: Queue<Pair<Int, Int>> = LinkedList()

    val starts = grid.listPositionsOf(WATER)
    queue += starts

    var time = 0
    while (queue.isNotEmpty()) {
        repeat(queue.size) {
            val (i, j) = queue.poll()
            if (invalidAt[i][j] != UNVISTED)
                return@repeat
            invalidAt[i][j] = time

            val neighbors = listOf(
                i + 1 to j,
                i - 1 to j,
                i to j + 1,
                i to j - 1,
            )
            for (neighbor in neighbors) {
                if (!neighbor.isInRange() || neighbor.isWall(grid) || neighbor.isEnd(grid))
                    continue
                queue.add(neighbor)
            }
        }
        ++time
    }

    return invalidAt
}

fun distanceOrNullWithBfs(r: Int, c: Int, grid: Array<String>, invalidAt: Array<IntArray>): Int? {
    fun Pair<Int, Int>.isInRange(): Boolean =
        first in 0 until r && second in 0 until c

    val distances = Array(r) { IntArray(c) { UNVISTED } }

    val queue: Queue<Pair<Int, Int>> = LinkedList()

    val start = grid.listPositionsOf(START).first()
    val end = grid.listPositionsOf(END).first()

    queue += start

    var time = 0
    while (queue.isNotEmpty()) {
        repeat(queue.size) {
            val now = queue.poll()

            if (now == end)
                return time

            val (i, j) = now
            if (invalidAt[i][j] <= time ||
                distances[i][j] != UNVISTED
            )
                return@repeat
            distances[i][j] = time

            val neighbors = listOf(
                i + 1 to j,
                i - 1 to j,
                i to j + 1,
                i to j - 1,
            )
            for (neighbor in neighbors) {
                if (!neighbor.isInRange() || neighbor.isWall(grid))
                    continue
                queue.add(neighbor)
            }
        }
        ++time
    }

    return null
}

fun solve(r: Int, c: Int, grid: Array<String>): Int? {
    val invalidAt = getInvalidAtWithBfs(r, c, grid)
    return distanceOrNullWithBfs(r, c, grid, invalidAt)
}

fun main() {
    val (r, c) = readln().split(" ").map { it.toInt() }
    val grid = Array(r) {
        readln()
    }

    val result = solve(r, c, grid)?.toString() ?: "KAKTUS"

    println(result)
}
