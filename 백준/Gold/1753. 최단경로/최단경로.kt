import java.util.*

const val MAX_VALUE = 1000000000

fun dijkstra(v: Int, start: Int, edges: Map<Int, List<Triple<Int, Int, Int>>>): IntArray {
    val distances = IntArray(v) { MAX_VALUE }
    val visited = BooleanArray(v)
    val priorityQueue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })

    distances[start] = 0
    priorityQueue.add(start to 0)

    while (priorityQueue.isNotEmpty()) {
        val (node, _) = priorityQueue.poll()

        if (visited[node])
            continue
        visited[node] = true

        val neighbors = edges[node]

        neighbors?.forEach { (_, neighbor, weight) ->
            val distance = distances[node] + weight

            if (distance >= distances[neighbor])
                return@forEach

            distances[neighbor] = distance
            priorityQueue.add(neighbor to distance)
        }
    }

    return distances
}

fun main() {
    val (v, e) = readln().split(" ").map { it.toInt() }
    val start = readln().toInt()
    val edges = List(e) {
        readln().split(" ").map { it.toInt() }
            .let { Triple(it[0], it[1], it[2]) }
    }

    val edgeMap = edges.groupBy { it.first }

    val result = dijkstra(v + 1, start, edgeMap)

    val resultString = result.drop(1).joinToString("\n") { if (it == MAX_VALUE) "INF" else it.toString() }
    println(resultString)
}