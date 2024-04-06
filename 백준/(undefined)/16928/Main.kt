import java.util.*
import kotlin.math.min

fun bfs(nVertexes: Int, edges: List<Pair<Int, Int>>, start: Int): Int {
    val visited = IntArray(nVertexes + 1)
    val toVisit: Queue<Pair<Int, Int>> = LinkedList<Pair<Int, Int>>().apply { add(start to 0) }

    while (toVisit.isNotEmpty()) {
        val (position, nMovements) = toVisit.poll()
        if (visited[position] == 0 || visited[position] > nMovements) {
            visited[position] = nMovements
            if (position == 100)
                continue
            val neighbors = (position + 1..min(position + 6, 100))
                .map { edges.find { edge -> edge.first == it }?.second ?: it }
                .map { it to nMovements + 1 }
            toVisit.addAll(neighbors)
        }
    }

    return visited[100]
}

fun main(args: Array<String>) {
    val (nLadders, nSnakes) = readln().split(" ").map { it.toInt() }

    val nVertexes = nLadders + nSnakes
    val edges = (1..(nVertexes)).map {
        readln()
            .split(" ")
            .map { it.toInt() }
            .let { it[0] to it[1] }
    }

    val bfsResult = bfs(100, edges, 1)
    println(bfsResult)
}
