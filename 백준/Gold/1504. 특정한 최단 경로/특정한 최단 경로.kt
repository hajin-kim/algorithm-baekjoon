import java.util.*

const val MAX = 700000000

fun dijkstra(n: Int, start: Int, edgeMap: Map<Int, List<Triple<Int, Int, Int>>>): IntArray {
    val visited = BooleanArray(n)
    val priorityQueue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })

    priorityQueue.add(start to 0)
    val distances = IntArray(n) { MAX }
    distances[start] = 0

    while (priorityQueue.isNotEmpty()) {
        val (now, distance) = priorityQueue.poll()

        if (visited[now])
            continue
        visited[now] = true

        edgeMap[now]?.forEach { (_, to, c) ->
            if (visited[to])
                return@forEach

            val newDistance = distance + c
            if (newDistance < distances[to]) {
                distances[to] = newDistance
                priorityQueue.add(to to newDistance)
            }
        }
    }

    return distances
}

fun main() {
    val (n, e) = readln().split(" ").map { it.toInt() }
    val edges = List(e) {
        readln().split(" ").map { it.toInt() }.let { Triple(it[0], it[1], it[2]) }
    }
    val (v1, v2) = readln().split(" ").map { it.toInt() }

    val edgeMap = edges
        .flatMap { listOf(it, Triple(it.second, it.first, it.third)) }
        .groupBy { it.first }

    val distancesFrom1 = dijkstra(n + 1, 1, edgeMap)
    val distancesFromV1 = dijkstra(n + 1, v1, edgeMap)
    val distancesFromV2 = dijkstra(n + 1, v2, edgeMap)

    val result = minOf(
        distancesFrom1[v2] + distancesFromV2[v1] + distancesFromV1[n],
        distancesFrom1[v1] + distancesFromV1[v2] + distancesFromV2[n],
    )

    if (result >= MAX) {
        println(-1)
        return
    }

    println(result)
}