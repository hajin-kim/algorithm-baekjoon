import java.util.*

fun main(args: Array<String>) {
    val (m, n) = readln().split(" ").map { it.toInt() }
    val state = (1..n)
        .map {
            readln().split(" ").map { it.toInt() }.toIntArray()
        }
        .toTypedArray()

    var nFresh = 0
    val initialRottenPositions = mutableListOf<Pair<Int, Int>>()
    state
        .withIndex()
        .forEach { (i, row) ->
            row.withIndex().forEach { (j, value) ->
                when (value) {
                    0 -> ++nFresh
                    1 -> initialRottenPositions.add(i to j)
                }
            }
        }

    val visited = Array(n) { BooleanArray(m) }
    var elapsedTime = 0
    var queue: Queue<Pair<Int, Int>> = LinkedList(initialRottenPositions)
    while (queue.isNotEmpty()) {
        val toVisit = LinkedList<Pair<Int, Int>>()

        queue.forEach { (i, j) ->
            if (i !in 0 until n || j !in 0 until m)
                return@forEach
            if (visited[i][j] || state[i][j] == -1)
                return@forEach

            visited[i][j] = true
            if (state[i][j] == 0)
                --nFresh
            if (nFresh == 0) {
                println(elapsedTime)
                return
            }

            toVisit.addAll(
                listOf(
                    i - 1 to j,
                    i + 1 to j,
                    i to j - 1,
                    i to j + 1,
                )
            )
        }

        ++elapsedTime
        queue = toVisit
    }

    println(-1)
}