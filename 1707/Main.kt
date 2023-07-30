import java.util.*

fun bfs(nVertexes: Int, edges: Map<Int, List<Int>>): Boolean {
    val visited = IntArray(nVertexes + 1).apply { this[0] = 1 }
    var toVisit: Queue<Int> = LinkedList()

    var level = 0
    for (i in 1..nVertexes) {
        if (visited[i] != 0) {
            continue
        }

        toVisit.add(i)
        while (toVisit.isNotEmpty()) {
            val toVisitOnNext: Queue<Int> = LinkedList()

            toVisit.forEach { node ->
                val visitedLevel = visited[node]

                if (visitedLevel == 0) {
                    visited[node] = level
                    val neighbors = edges[node] ?: emptyList()
                    toVisitOnNext.addAll(neighbors)
                } else if (visitedLevel % 2 != level % 2)
                    return false
            }

            toVisit = toVisitOnNext
            ++level
        }
    }

    return true
}

fun main(args: Array<String>) {
    val n = readln().toInt()
    repeat(n) {
        val (nVertexes, nEdges) = readln().split(" ").map { it.toInt() }
        val edges = (1..nEdges)
            .flatMap {
                readln()
                    .split(" ")
                    .map { it.toInt() }
                    .let { listOf(it[0] to it[1], it[1] to it[0]) }
            }
            .distinct()
            .groupBy({ it.first }, { it.second })

        val bfsResult = bfs(nVertexes, edges)
        println(if (bfsResult) "YES" else "NO")
    }
}
