import java.util.*

fun findPath(start: Int, end: Int, parents: IntArray): List<Int> {
    val path = mutableListOf<Int>()
    var current = end
    while (current != start) {
        path.add(current)
        current = parents[current]
    }
    path.add(start)
    return path.reversed()
}

fun bfs(start: Int, end: Int): List<Int> {
    val parents = IntArray(100001) { -1 }
    var queue: Queue<Int> = LinkedList()

    queue.add(start)
    var distance = 0
    while (queue.isNotEmpty()) {
        val toVisit: Queue<Int> = LinkedList()

        queue.forEach {
            if (it == end) {
                return findPath(start, end, parents)
            }
            mutableListOf(it - 1, it + 1, it * 2)
                .filter { n -> n in 0..100000 && parents[n] == -1 }
                .forEach { n ->
                    parents[n] = it
                    toVisit.add(n)
                }
        }

        queue = toVisit
        ++distance
    }

    return emptyList()
}

fun main(args: Array<String>) {
    val (n, k) = readln().split(" ").map { it.toInt() }

    val result = bfs(n, k)

    println(result.size - 1)
    println(result.joinToString(" "))
}
