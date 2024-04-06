import java.util.*


fun bfs(n: Int, edges: List<Pair<Int, Int>>, start: Int): Int {
    val visited = BooleanArray(n + 1).apply { this[0] = true }
    var toVisit: Queue<Int> = LinkedList<Int>().apply { add(start) }

    var score = 0
    while (toVisit.isNotEmpty()) {
        val nextToVisit = LinkedList<Int>()

        toVisit.forEach { node ->
            if (visited[node])
                return@forEach
            visited[node] = true

            val neighbors = edges
                .filter { it.first == node || it.second == node }
                .flatMap { listOf(it.first, it.second) }
                .filter { it != node }
            nextToVisit.addAll(neighbors)
        }

        if (visited.all { it })
            break

        toVisit = nextToVisit
        ++score
    }

    return score
}

fun main(args: Array<String>) {
    val n = readln().toInt()
    val edges = mutableListOf<Pair<Int, Int>>()
    while (true) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        if (a == -1 && b == -1)
            break
        edges.add(a to b)
    }

    val scores = (1..n).map { it to bfs(n, edges, it) }
    val minScore = scores.minBy { it.second }.second
    val candidates = scores.filter { it.second == minScore }.map { it.first }

    println("$minScore ${candidates.size}")
    println(candidates.joinToString(" "))
}