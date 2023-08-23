import java.util.*

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val edges = (1..m).flatMap {
        readln()
            .split(" ")
            .drop(1)
            .map { it.toInt() }
            .zipWithNext { a, b -> a to b }
    }

    val neighbors = edges.groupBy({ it.first }, { it.second })
    val remainingEdges = run {
        val reversedNeighbors = edges.groupBy({ it.second }, { it.first })
        IntArray(n + 1) { reversedNeighbors[it]?.size ?: 0 }
    }

    val queue: Queue<Int> = LinkedList()
    val result = mutableListOf<Int>()

    remainingEdges
        .withIndex()
        .drop(1)
        .filter { it.value == 0 }
        .forEach { queue.add(it.index) }

    while (queue.isNotEmpty()) {
        val node = queue.poll()
        result.add(node)
        neighbors[node]?.forEach { dest ->
            --remainingEdges[dest]
            if (remainingEdges[dest] == 0) {
                queue.add(dest)
            }
        }
    }

    if (result.size != n) {
        println(0)
        return
    }

    println(result.joinToString(" "))
}