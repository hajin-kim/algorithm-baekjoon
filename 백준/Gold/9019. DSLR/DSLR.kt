import java.util.LinkedList
import java.util.Queue

const val MAX = 10000
fun Int.d(): Int = (this * 2) % MAX

fun Int.s(): Int = (this + MAX - 1) % MAX

fun Int.l(): Int {
    var result = (this * 10) % MAX
    result += this / 1000
    return result
}

fun Int.r(): Int {
    var result = this / 10
    result += (this % 10) * 1000
    return result
}

fun precalculateEdgeArray(): Array<List<Pair<Int, Char>>> {
    return Array(MAX) {
        listOf(
            it.d() to 'D',
            it.s() to 'S',
            it.l() to 'L',
            it.r() to 'R',
        )
    }
}

fun solve(edgeArray: Array<List<Pair<Int, Char>>>, start: Int, end: Int): List<Char> {
    val discovered = BooleanArray(MAX)
    val queue: Queue<Pair<Int, List<Char>>> = LinkedList()

    discovered[start] = true
    queue.add(start to emptyList())

    var nextDistance = 1
    while (queue.isNotEmpty()) {
        repeat(queue.size) {
            val (number, commands) = queue.poll()

            val neighbors = edgeArray[number]

            for ((neighbor, command) in neighbors) {
                if (discovered[neighbor])
                    continue

                val nextCommand = commands + command
                if (neighbor == end)
                    return nextCommand

                discovered[neighbor] = true
                queue.add(neighbor to nextCommand)
            }
        }
        ++nextDistance
    }

    return emptyList()
}

fun main() {
    val edgeArray = precalculateEdgeArray()

    val t = readln().toInt()

    repeat(t) {
        val (start, end) = readln().split(" ").map { it.toInt() }

        val result = solve(edgeArray, start, end)

        println(result.joinToString(""))
    }
}