import java.util.LinkedList
import java.util.Queue

fun bfs(v: Int, start: Int, edgeMap: Map<Int, List<Int>>): Int {
    val discovered = BooleanArray(v)
    val queue: Queue<Int> = LinkedList()

    discovered[start] = true
    queue.add(start)

    var nowDistance = -1
    while (queue.isNotEmpty()) {
        ++nowDistance
        repeat(queue.size) {
            val now = queue.poll()
            val neighbors = edgeMap[now] ?: emptyList()
            for (neighbor in neighbors) {
                if (discovered[neighbor])
                    continue

                discovered[neighbor] = true
                queue.add(neighbor)
            }
        }
    }
    return discovered.count { it } - 1
}

fun solve(v: Int, edges: List<Pair<Int, Int>>): Int {
    val edgeMap = edges
        .flatMap {
            listOf(it, it.second to it.first)
        }
        .groupBy({ it.first }, { it.second })

    return bfs(v + 1, 1, edgeMap)
}

fun main() {
    val v = readln().toInt()
    val e = readln().toInt()
    val edges = List(e) {
        readln().split(" ").map { it.toInt() }.let { it[0] to it[1] }
    }

    val result = solve(v, edges)

    println(result)
}