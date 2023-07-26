import java.util.*
import kotlin.math.max
import kotlin.math.min

fun getNeighborhoods(edges: List<Pair<Int, Int>>, node: Int): List<Int> {
    val firstNeighbors = edges
        .filter { it.second == node }
        .map { it.first }
    val secondNeighbors = edges
        .filter { it.first == node }
        .map { it.second }
    return firstNeighbors.plus(secondNeighbors).sorted()
}

fun bfs(nVertexes: Int, edges: List<Pair<Int, Int>>, start: Int): Int {
    val visited = IntArray(nVertexes + 1)
    val toVisit: Queue<Pair<Int, Int>> = LinkedList<Pair<Int, Int>>().apply { add(start to 0) }
    val result = mutableListOf<Int>()

    while (toVisit.isNotEmpty()) {
        val (node, steps) = toVisit.poll()
        if (visited[node] == 0 || visited[node] > steps) {
            visited[node] = steps
            result.add(node)
            val neighbors = getNeighborhoods(edges, node)
                .map { it to steps + 1 }
            toVisit.addAll(neighbors)
        }
    }

    return visited.sum()
}

fun main(args: Array<String>) {
    val (nVertices, nEdges) = readln().split(" ").map { it.toInt() }
    val edges = (1..nEdges)
        .map {
            readln()
                .split(" ")
                .map { it.toInt() }
                .let { min(it[0], it[1]) to max(it[0], it[1]) }
        }
        .distinct()

    val bfsResult = (1..nVertices)
        .map { it to bfs(nVertices, edges, it) }
        .minWith { o1, o2 ->
            when (o1.second) {
                o2.second -> o1.first.compareTo(o2.first)
                else -> o1.second.compareTo(o2.second)
            }
        }

    println(bfsResult.first)
}