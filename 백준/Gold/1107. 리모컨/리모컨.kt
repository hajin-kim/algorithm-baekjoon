import java.util.LinkedList
import java.util.Queue
import kotlin.math.absoluteValue

fun Int.distance(other: Int): Int = (this - other).absoluteValue

fun solve(n: Int, disabledEdges: Set<Int>): Int {
    var result = n.distance(100)

    val edges = (0..9).toSet()
    val enabledEdges = edges - disabledEdges

    val queue: Queue<Int> = LinkedList()
    queue.addAll(enabledEdges)

    var pushed = 0
    while (queue.isNotEmpty()) {
        ++pushed
        repeat(queue.size) {
            val now = queue.poll()
            val distance = n.distance(now) + pushed

            result = minOf(result, distance)

            if (now == 0 || now >= 100_000)
                return@repeat

            val neighbors = enabledEdges.map { digit ->
                now * 10 + digit
            }
            queue.addAll(neighbors)
        }
    }

    return result
}

fun main() {
    val n = readln().toInt()
    val m = readln().toInt()
    val disabledEdges = if (m == 0) emptySet() else
        readln().split(" ").map { it.toInt() }.toSet()

    val result = solve(n, disabledEdges)

    println(result)
}