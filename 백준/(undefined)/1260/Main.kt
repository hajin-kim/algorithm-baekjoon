import java.util.*

fun getNeighborhoods(edges: List<Pair<Int, Int>>, node: Int): List<Int> {
    val firstNeighbors = edges
        .filter { it.second == node }
        .map { it.first }
    val secondNeighbors = edges
        .filter { it.first == node }
        .map { it.second }
    return firstNeighbors.plus(secondNeighbors).sorted()
}

fun dfs(nVertexes: Int, edges: List<Pair<Int, Int>>, start: Int): List<Int> {
    val visited = BooleanArray(nVertexes + 1)
    val toVisit: Stack<Int> = Stack<Int>().apply { push(start) }
    val result = mutableListOf<Int>()

    while (toVisit.isNotEmpty()) {
        val node = toVisit.pop()
        if (!visited[node]) {
            visited[node] = true
            result.add(node)
            val neighbors = getNeighborhoods(edges, node).reversed()
            toVisit.addAll(neighbors)
        }
    }

    return result
}

fun bfs(nVertexes: Int, edges: List<Pair<Int, Int>>, start: Int): List<Int> {
    val visited = BooleanArray(nVertexes + 1)
    var toVisit: Queue<Int> = LinkedList<Int>().apply { add(start) }
    val result = mutableListOf<Int>()

    while (toVisit.isNotEmpty()) {
        val toVisitOnNext: Queue<Int> = LinkedList()

        toVisit.forEach { node ->
            if (!visited[node]) {
                visited[node] = true
                result.add(node)
                val neighbors = getNeighborhoods(edges, node)
                toVisitOnNext.addAll(neighbors)
            }
        }

        toVisit = toVisitOnNext
    }

    return result
}

fun main(args: Array<String>) {
    val (nVertexes, nEdges, start) = readln().split(" ").map { it.toInt() }
    val edges = (1..nEdges).map {
        readln()
            .split(" ")
            .map { it.toInt() }
            .let { it[0] to it[1] }
    }

    val dfsResult = dfs(nVertexes, edges, start)
    println(dfsResult.joinToString(" "))

    val bfsResult = bfs(nVertexes, edges, start)
    println(bfsResult.joinToString(" "))
}
