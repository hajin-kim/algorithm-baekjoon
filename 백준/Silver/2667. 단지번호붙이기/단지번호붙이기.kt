import java.util.LinkedList
import java.util.Queue

fun isInRange(n: Int, r: Int, c: Int): Boolean =
    r in 0 until n && c in 0 until n

fun mutableBfs(n: Int, isUncheckedHouse: Array<BooleanArray>, start: Pair<Int, Int>): Int {
    val queue: Queue<Pair<Int, Int>> = LinkedList()

    queue.add(start)
    isUncheckedHouse[start.first][start.second] = false

    var count = 0
    while (queue.isNotEmpty()) {
        count += queue.size
        repeat(queue.size) {
            val (r, c) = queue.poll()

            val neighbors = listOf(
                r + 1 to c,
                r - 1 to c,
                r to c + 1,
                r to c - 1,
            )

            for (neighbor in neighbors) {
                val (nr, nc) = neighbor

                if (!isInRange(n, nr, nc) || !isUncheckedHouse[nr][nc])
                    continue

                queue.add(neighbor)
                isUncheckedHouse[nr][nc] = false
            }
        }
    }

    return count
}

fun solve(n: Int, isUncheckedHouse: Array<BooleanArray>): List<Int> {

    val result = mutableListOf<Int>()

    for (r in 0 until n) {
        for (c in 0 until n) {
            if (isUncheckedHouse[r][c]) {
                val nHouses = mutableBfs(n, isUncheckedHouse, r to c)
                result.add(nHouses)
            }
        }
    }

    return result.sorted()
}

fun main() {
    val n = readln().toInt()
    val isHouse = Array(n) {
        readln().map { it == '1' }.toBooleanArray()
    }

    val result = solve(n, isHouse)

    println(result.size)
    println(result.joinToString("\n"))
}