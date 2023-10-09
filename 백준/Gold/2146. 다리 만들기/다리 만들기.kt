import java.util.*

fun getIds(n: Int, map: Array<CharArray>): Pair<Array<IntArray>, MutableList<Pair<Int, Int>>> {
    fun isInRange(y: Int, x: Int) =
        y in 0 until n && x in 0 until n

    val visited = Array(n) { BooleanArray(n) }
    val ids = Array(n) { IntArray(n) { -1 } }

    val initialPoints = mutableListOf<Pair<Int, Int>>()

    fun bfs(start: Pair<Int, Int>, id: Int) {
        val queue: Queue<Pair<Int, Int>> = LinkedList()
        visited[start.first][start.second] = true
        queue.add(start)

        while (queue.isNotEmpty()) {
            val (y, x) = queue.poll()

            ids[y][x] = id

            listOf(
                y - 1 to x,
                y + 1 to x,
                y to x - 1,
                y to x + 1,
            ).forEach { (ny, nx) ->
                if (!isInRange(ny, nx))
                    return@forEach
                if (visited[ny][nx] || map[ny][nx] != '1')
                    return@forEach

                visited[ny][nx] = true
                queue.add(ny to nx)
            }
        }
    }

    var currentId = 0

    for (y in 0 until n) {
        for (x in 0 until n) {
            if (!visited[y][x] && map[y][x] == '1') {
                bfs(y to x, currentId++)
                initialPoints.add(y to x)
            }
        }
    }

    return ids to initialPoints
}

fun getMinDistance(n: Int, ids: Array<IntArray>, initialPoints: List<Pair<Int, Int>>): Int {
    fun isInRange(y: Int, x: Int) =
        y in 0 until n && x in 0 until n

    val MAX = 20000
    var result = MAX

    fun dijkstra(start: Pair<Int, Int>, id: Int) {
        val priorityQueue = PriorityQueue<Triple<Int, Int, Int>>(compareBy { it.third })
        priorityQueue.add(Triple(start.first, start.second, 0))

        val distances = Array(n) { IntArray(n) { MAX } }
        distances[start.first][start.second] = 0

        val visited = Array(n) { BooleanArray(n) }
        while (priorityQueue.isNotEmpty()) {
            val (y, x, distance) = priorityQueue.poll()

            if (visited[y][x])
                continue

            visited[y][x] = true

            listOf(
                y - 1 to x,
                y + 1 to x,
                y to x - 1,
                y to x + 1,
            ).forEach { (ny, nx) ->
                if (!isInRange(ny, nx))
                    return@forEach
                if (visited[ny][nx])
                    return@forEach

                if (ids[ny][nx] != -1 && ids[ny][nx] != id) {
                    result = minOf(result, distance)
                    return@forEach
                }

                val newDistance = distance + if (ids[ny][nx] == id) 0 else 1

                if (newDistance < distances[ny][nx]) {
                    distances[ny][nx] = newDistance
                    priorityQueue.add(Triple(ny, nx, newDistance))
                }
            }
        }

        start
    }

    initialPoints.forEachIndexed { index, start ->
        dijkstra(start, index)
    }

    return result
}


fun main() {
    val n = readln().toInt()
    val map = Array(n) {
        readln().split(" ").map { it[0] }.toCharArray()
    }

    val (ids, initialPoints) = getIds(n, map)

    val result = getMinDistance(n, ids, initialPoints)

    println(result)
}