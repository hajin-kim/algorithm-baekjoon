import java.util.*

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val arr = Array(n) {
        readln().split(" ").map { it.toInt() }.toIntArray()
    }

    var glacial: Queue<Pair<Int, Int>> = arr
        .flatMapIndexedTo(LinkedList()) { rowIndex, row ->
            row.withIndex()
                .filter { it.value != 0 }
                .map { rowIndex to it.index }
        }

    fun bfs(start: Pair<Int, Int>): Int {
        val visited = Array(n) { BooleanArray(m) }
        val queue: Queue<Pair<Int, Int>> = LinkedList()

        queue.add(start)

        var count = 0
        while (queue.isNotEmpty()) {
            val (r, c) = queue.poll()

            if (visited[r][c])
                continue
            if (arr[r][c] == 0)
                continue

            ++count
            visited[r][c] = true

            queue.addAll(
                listOf(
                    r + 1 to c,
                    r - 1 to c,
                    r to c + 1,
                    r to c - 1,
                )
            )
        }

        return count
    }

    var count = 0
    while (true) {
        val newGlacial: Queue<Pair<Int, Int>> = LinkedList()

        glacial.map { (r, c) ->
            val zeros = listOf(
                r + 1 to c,
                r - 1 to c,
                r to c + 1,
                r to c - 1,
            ).count { (nr, nc) ->
                arr[nr][nc] == 0
            }

            Triple(r, c, zeros)
        }.forEach { (r, c, zeros) ->
            val new = maxOf(arr[r][c] - zeros, 0)
            arr[r][c] = new

            if (new > 0)
                newGlacial.add(r to c)
        }

        ++count
        glacial = newGlacial

        if (glacial.isEmpty()) {
            println("0")
            return
        }
        if (bfs(glacial.first()) != glacial.size) {
            println(count)
            return
        }
    }
}
