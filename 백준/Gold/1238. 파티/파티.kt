import java.util.*

const val INFINITE = 100000

fun dijkstra(n: Int, start: Int, graph: Array<IntArray>): IntArray {
    val distances = IntArray(n + 1) { INFINITE }
    distances[start] = 0
    val visited = BooleanArray(n + 1)
    val queue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
    queue.add(start to 0)

    while (queue.isNotEmpty()) {
        val (a, _) = queue.poll()
        if (visited[a])
            continue
        visited[a] = true

        for (b in 1..n) {
            if (graph[a][b] == INFINITE)
                continue
            val newDistance = distances[a] + graph[a][b]
            if (distances[b] > newDistance) {
                distances[b] = newDistance
                queue.add(b to newDistance)
            }
        }
    }

    return distances
}

fun main() {
    val (n, m, x) = readln().split(" ").map { it.toInt() }
    val edges = Array(m) { readln().split(" ").map { it.toInt() } }

    val graph = Array(n + 1) { y -> IntArray(n + 1) { x -> if (y == x) 0 else INFINITE } }
    val reversedGraph = Array(n + 1) { y -> IntArray(n + 1) { x -> if (y == x) 0 else INFINITE } }
    edges.forEach { (a, b, w) ->
        graph[a][b] = w
        reversedGraph[b][a] = w
    }

    val distances = dijkstra(n, x, graph)
    val reversedDistances = dijkstra(n, x, reversedGraph)

    val result = (1..n).maxOf {
        distances[it] + reversedDistances[it]
    }

    println(result)
}