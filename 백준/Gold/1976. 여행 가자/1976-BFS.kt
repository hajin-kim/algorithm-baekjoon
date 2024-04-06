import java.util.LinkedList
import java.util.Queue

fun solve(n: Int, edgeArray: Array<BooleanArray>, plan: List<Int>): Boolean {
    @Suppress("NAME_SHADOWING")
    val plan = plan.map { it - 1 }

    val discovered = BooleanArray(n)
    val queue: Queue<Int> = LinkedList()

    val start = plan.first()
    discovered[start] = true
    queue.add(start)

    while (queue.isNotEmpty()) {
        val city = queue.poll()

        val neighbors = edgeArray[city]
            .withIndex()
            .filter { it.value }
            .map { it.index }

        for (neighbor in neighbors) {
            if (discovered[neighbor])
                continue
            discovered[neighbor] = true
            queue.add(neighbor)
        }
    }

    return plan.all { discovered[it] }
}

fun main() {
    val n = readln().toInt()
    val m = readln().toInt()

    val edgeArray = Array(n) {
        readln().split(" ").map { it == "1" }.toBooleanArray()
    }

    val plan = readln().split(" ").map { it.toInt() }

    val result = solve(n, edgeArray, plan)

    println(if (result) "YES" else "NO")
}
