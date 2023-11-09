import java.util.LinkedList
import java.util.Queue

fun bfs(v: Int, start: Int, end: Int, edgeMap: Map<Int, List<Int>>): Int {
    val discovered = BooleanArray(v)
    val queue: Queue<Int> = LinkedList()

    discovered[start] = true
    queue.add(start)

    var nowDistance = -1
    while (queue.isNotEmpty()) {
        ++nowDistance
        repeat(queue.size) {
            val now = queue.poll()

            if (now == end)
                return nowDistance

            val neighbors = edgeMap[now] ?: emptyList()
            for (neighbor in neighbors) {
                if (discovered[neighbor])
                    continue

                discovered[neighbor] = true
                queue.add(neighbor)
            }
        }
    }
    return -1
}

fun solve(v: Int, start: Int, end: Int, edges: List<Pair<Int, Int>>): Int {
    val edgeMap = edges
        .flatMap {
            listOf(it, it.second to it.first)
        }
        .groupBy({ it.first }, { it.second })

    return bfs(v + 1, start, end, edgeMap)
}

fun main() {
    val v = readln().toInt()
    val (start, end) = readln().split(" ").map { it.toInt() }
    val e = readln().toInt()
    val edges = List(e) {
        readln().split(" ").map { it.toInt() }.let { it[0] to it[1] }
    }

    val result = solve(v, start, end, edges)

    println(result)
}