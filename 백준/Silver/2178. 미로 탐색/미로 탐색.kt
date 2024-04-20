import java.util.ArrayDeque
import java.util.Queue

fun solve(n: Int, m: Int, grid: Array<BooleanArray>): Int {
    val queue: Queue<Pair<Int, Int>> = ArrayDeque()
    val visited = Array(n) { BooleanArray(m) }

    queue += 0 to 0

    var distance = 1
    while (queue.isNotEmpty()) {
        repeat(queue.size) {
            val (r, c) = queue.poll()

            if (r == n - 1 && c == m - 1)
                return distance

            if (r !in 0 until n || c !in 0 until m)
                return@repeat
            if (visited[r][c])
                return@repeat
            if (!grid[r][c])
                return@repeat

            visited[r][c] = true

            val neighbors = listOf(
                r to c + 1,
                r to c - 1,
                r + 1 to c,
                r - 1 to c,
            )
            for (neighbor in neighbors) {
                queue += neighbor
            }
        }
        ++distance
    }

    return -1
}

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val grid = Array(n) {
        readln().map { it == '1' }.toBooleanArray()
    }

    val result = solve(n, m, grid)

    println(result)
}
