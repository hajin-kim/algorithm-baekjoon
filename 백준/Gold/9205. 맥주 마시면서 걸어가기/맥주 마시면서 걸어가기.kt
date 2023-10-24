import java.util.*
import kotlin.math.abs

infix fun Pair<Int, Int>.manhattanDistance(other: Pair<Int, Int>): Int =
    abs(this.first - other.first) + abs(this.second - other.second)

fun solve(
    n: Int,
    start: Pair<Int, Int>,
    end: Pair<Int, Int>,
    stores: Array<Pair<Int, Int>>,
): Boolean {
    val discovered = BooleanArray(n)
    val queue: Queue<Pair<Int, Int>> = LinkedList()

    queue.add(start)

    while (queue.isNotEmpty()) {
        val now = queue.poll()
        if (now manhattanDistance end <= 1000)
            return true

        for (i in stores.indices) {
            if (discovered[i])
                continue
            if (now manhattanDistance stores[i] <= 1000) {
                discovered[i] = true
                queue.add(stores[i])
            }
        }
    }

    return false
}

fun main() {
    val t = readln().toInt()
    repeat(t) {
        val n = readln().toInt()
        val start = readln().split(" ").map { it.toInt() }.let { it[0] to it[1] }
        val stores = Array(n) {
            readln().split(" ").map { it.toInt() }.let { it[0] to it[1] }
        }
        val end = readln().split(" ").map { it.toInt() }.let { it[0] to it[1] }

        val result = solve(
            n = n,
            start = start,
            end = end,
            stores = stores,
        )

        println(
            if (result) "happy" else "sad"
        )
    }
}