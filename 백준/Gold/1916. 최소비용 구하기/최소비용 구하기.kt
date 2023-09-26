import java.util.*

const val MAX_VALUE = 1000000000

fun dijkstra(n: Int, start: Int, edges: Map<Int, List<Pair<Int, Int>>>): IntArray {
    val distances = IntArray(n) { MAX_VALUE }
    val visited = BooleanArray(n)
    val priorityQueue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })

    distances[start] = 0
    priorityQueue.add(start to 0)

    while (priorityQueue.isNotEmpty()) {
        val (node, _) = priorityQueue.poll()

        if (visited[node])
            continue
        visited[node] = true

        val neighbors = edges[node]
        neighbors?.forEach { (neighbor, weight) ->
            val distance = distances[node] + weight
            if (distance < distances[neighbor]) {
                distances[neighbor] = distance
                priorityQueue.add(neighbor to distance)
            }
        }
    }

    return distances
}

fun main() {
    val n = readln().toInt()
    val m = readln().toInt()

    val edges = List(m) { readln().split(" ").map { it.toInt() } }.groupBy({ it[0] }, { it[1] to it[2] })

    val (start, end) = readln().split(" ").map { it.toInt() }

    val distances = dijkstra(n + 1, start, edges)

    println(distances[end])
}
