import java.util.*

fun bfs(start: Int, end: Int): Int {
    val visited = BooleanArray(100001)
    var queue: Queue<Int> = LinkedList()

    queue.add(start)
    var distance = 0
    while (queue.isNotEmpty()) {
        val toVisit: Queue<Int> = LinkedList()

        while (queue.isNotEmpty()) {
            val it = queue.poll()
            if (it < 0 || it > 100000) {
                continue
            }
            if (it == end) {
                return distance
            }
            if (!visited[it]) {
                visited[it] = true
                queue.add(it * 2)
                val neighbors = mutableListOf(it - 1, it + 1)
                toVisit.addAll(neighbors)
            }
        }

        queue = toVisit
        ++distance
    }

    return -1
}

fun main(args: Array<String>) {
    val (n, k) = readln().split(" ").map { it.toInt() }

    val result = bfs(n, k)

    println(result)
}
