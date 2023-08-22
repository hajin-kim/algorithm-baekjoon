import java.util.*

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val edges = List(m) {
        readln().split(" ").map { it.toInt() }.let { it[0] to it[1] }
    }

    val neighbors = edges.groupBy({ it.first }, { it.second })
    val remainingEdges = run {
        val reversedNeighbors = edges.groupBy({ it.second }, { it.first })
        IntArray(n + 1) { reversedNeighbors[it]?.size ?: 0 }
    }

    val priorityQueue: PriorityQueue<Int> = PriorityQueue()
    val result = mutableListOf<Int>()

    remainingEdges
        .withIndex()
        .drop(1)
        .filter { it.value == 0 }
        .forEach { priorityQueue.add(it.index) }

    while (priorityQueue.isNotEmpty()) {
        val node = priorityQueue.poll()
        result.add(node)
        neighbors[node]?.forEach { dest ->
            --remainingEdges[dest]
            if (remainingEdges[dest] == 0) {
                priorityQueue.add(dest)
            }
        }
    }

    println(result.joinToString(" "))
}