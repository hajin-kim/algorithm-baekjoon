import java.util.*

fun bfs(start: Int, end: Int): Pair<Int, Int> {
    val visited = BooleanArray(100001)
    var queue: Queue<Int> = LinkedList()

    queue.add(start)
    var distance = 0
    while (queue.isNotEmpty()) {
        val toVisit: Queue<Int> = LinkedList()

        queue.forEach {
            if (it == end) {
                return distance to queue.count { q -> q == end }
            }
            visited[it] = true
            val neighbors = listOf(it - 1, it + 1, it * 2).filter { n ->
                n in 0..100000 && !visited[n]
            }
            toVisit.addAll(neighbors)
        }

        queue = toVisit
        ++distance
    }

    return -1 to -1
}

fun main(args: Array<String>) {
    val (n, k) = readln().split(" ").map { it.toInt() }

    val result = bfs(n, k)

    println(result.first)
    println(result.second)
}
