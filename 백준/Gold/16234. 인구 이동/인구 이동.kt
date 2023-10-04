import java.util.*
import kotlin.math.abs

fun main() {
    val (n, l, r) = readln().split(" ").map { it.toInt() }
    val populations = Array(n) {
        readln().split(" ").map { it.toInt() }.toIntArray()
    }

    val neighborRange = l..r

    fun isInRange(r: Int, c: Int): Boolean =
        r in 0 until n && c in 0 until n

    // r, c
    fun bfs(start: Pair<Int, Int>, visited: Array<BooleanArray>): Boolean {
        visited[start.first][start.second] = true

        val queue: Queue<Pair<Int, Int>> = LinkedList()
        queue.add(start)

        val connectedCountries = mutableListOf<Pair<Int, Int>>()
        connectedCountries.add(start)

        while (queue.isNotEmpty()) {
            val (r, c) = queue.poll()

            listOf(
                r + 1 to c,
                r - 1 to c,
                r to c - 1,
                r to c + 1,
            ).forEach {
                val (nr, nc) = it

                if (!isInRange(nr, nc))
                    return@forEach

                if (abs(populations[nr][nc] - populations[r][c]) !in neighborRange)
                    return@forEach

                if (visited[nr][nc])
                    return@forEach
                visited[nr][nc] = true

                connectedCountries.add(it)
                queue.add(it)
            }
        }

        if (connectedCountries.size == 1)
            return false

        val resultPopulation = connectedCountries
            .sumOf { (r, c) -> populations[r][c] }
            .div(connectedCountries.size)

        connectedCountries.forEach { (r, c) ->
            populations[r][c] = resultPopulation
        }

        return true
    }

    var count = 0
    var isMovementOccurred = false
    do {
        isMovementOccurred = false
        val visited = Array(n) { BooleanArray(n) }

        (0 until n).forEach { r ->
            (0 until n).forEach { c ->
                if (!visited[r][c])
                    isMovementOccurred = bfs(r to c, visited) || isMovementOccurred
            }
        }

        if (!isMovementOccurred)
            break

        count++
    } while (true)

    println(count)
}