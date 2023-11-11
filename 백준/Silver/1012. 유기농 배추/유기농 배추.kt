import java.util.LinkedList
import java.util.Queue

fun isInRange(n: Int, m: Int, r: Int, c: Int): Boolean =
    r in 0 until n && c in 0 until m

fun mutableBfs(n: Int, m: Int, isUncheckedHouse: Array<BooleanArray>, start: Pair<Int, Int>): Int {
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

                if (!isInRange(n, m, nr, nc) || !isUncheckedHouse[nr][nc])
                    continue

                queue.add(neighbor)
                isUncheckedHouse[nr][nc] = false
            }
        }
    }

    return count
}

fun solve(n: Int, m: Int, cabbages: List<Pair<Int, Int>>): Int {
    val isUncheckedCabbage = Array(n) { BooleanArray(m) }
    cabbages.forEach { (r, c) ->
        isUncheckedCabbage[r][c] = true
    }

    val result = mutableListOf<Int>()

    for (r in 0 until n) {
        for (c in 0 until m) {
            if (isUncheckedCabbage[r][c]) {
                val nHouses = mutableBfs(n, m, isUncheckedCabbage, r to c)
                result.add(nHouses)
            }
        }
    }

    return result.size
}

fun main() {
    val t = readln().toInt()

    repeat(t) {
        val (m, n, k) = readln().split(" ").map { it.toInt() }
        val cabbages = List(k) {
            readln().split(" ").map { it.toInt() }
                // r to c
                .let { it[1] to it[0] }
        }

        val result = solve(
            n = n,
            m = m,
            cabbages = cabbages
        )

        println(result)
    }
}