import java.util.*

fun main(args: Array<String>) {
    val (m, n, h) = readln().split(" ").map { it.toInt() }
    val state = (1..h).map {
        (1..n)
            .map {
                readln().split(" ").map { it.toInt() }.toIntArray()
            }
            .toTypedArray()
    }.toTypedArray()

    var nFresh = 0
    val initialRottenPositions = mutableListOf<Triple<Int, Int, Int>>()
    state.withIndex().forEach { (i, plane) ->
        plane.withIndex().forEach { (j, row) ->
            row.withIndex().forEach { (k, value) ->
                when (value) {
                    0 -> ++nFresh
                    1 -> initialRottenPositions.add(Triple(i, j, k))
                }
            }
        }
    }

    val visited = Array(h) { Array(n) { BooleanArray(m) } }
    var elapsedTime = 0
    var queue: Queue<Triple<Int, Int, Int>> = LinkedList(initialRottenPositions)
    while (queue.isNotEmpty()) {
        val toVisit = LinkedList<Triple<Int, Int, Int>>()

        queue.forEach { (i, j, k) ->
            if (i !in 0 until h || j !in 0 until n || k !in 0 until m)
                return@forEach
            if (visited[i][j][k] || state[i][j][k] == -1)
                return@forEach

            visited[i][j][k] = true
            if (state[i][j][k] == 0)
                --nFresh
            if (nFresh == 0) {
                println(elapsedTime)
                return
            }

            toVisit.addAll(
                listOf(
                    Triple(i - 1, j, k),
                    Triple(i + 1, j, k),
                    Triple(i, j - 1, k),
                    Triple(i, j + 1, k),
                    Triple(i, j, k - 1),
                    Triple(i, j, k + 1),
                )
            )
        }

        ++elapsedTime
        queue = toVisit
    }

    println(-1)
}