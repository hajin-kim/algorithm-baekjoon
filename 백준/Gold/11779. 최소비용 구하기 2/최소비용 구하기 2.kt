import java.util.*

const val MAX = 1000000000

fun dijkstra(n: Int, start: Int, edges: Map<Int, List<Pair<Int, Int>>>): Pair<IntArray, IntArray> {
    val visited = BooleanArray(n)

    val parents = IntArray(n) { -1 }
    parents[start] = start

    // node to cost
    val priorityQueue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
    priorityQueue.add(start to 0)

    val distances = IntArray(n) { MAX }
    distances[start] = 0

    while (priorityQueue.isNotEmpty()) {
        val (current, distance) = priorityQueue.poll()

        if (visited[current])
            continue
        visited[current] = true

        edges[current]?.forEach { (new, weight) ->
            if (visited[new])
                return@forEach

            val newDistance = distance + weight
            if (newDistance >= distances[new])
                return@forEach

            distances[new] = newDistance
            parents[new] = current
            priorityQueue.add(new to newDistance)
        }
    }

    return distances to parents
}

fun main() {
    val n = readln().toInt()
    val m = readln().toInt()

    val buses = List(m) {
        readln().split(" ").map { it.toInt() }.let { Triple(it[0], it[1], it[2]) }
    }
        .groupBy { it.first }
        .mapValues { (_, v) -> v.map { it.second to it.third } }

    val (start, end) = readln().split(" ").map { it.toInt() }.let { it[0] to it[1] }

    val (distances, parents) = dijkstra(n + 1, start, buses)

    val distance = distances[end]
    val path = LinkedList<Int>()

    var now = end
    while (now != start) {
        path.addFirst(now)
        now = parents[now]
    }
    path.addFirst(start)

    println(distance)
    println(path.size)
    println(path.joinToString(" "))
}