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

fun bfs(edgeArray: Array<List<Pair<Int, Char>>>, start: Int, end: Int): Pair<IntArray, CharArray> {
    val discovered = BooleanArray(MAX)
    val queue: Queue<Int> = LinkedList()

    val parents = IntArray(MAX)
    val commands = CharArray(MAX)

    discovered[start] = true
    queue.add(start)
    parents[start] = start

    while (queue.isNotEmpty()) {
        val number = queue.poll()

        val neighbors = edgeArray[number]

        for ((neighbor, command) in neighbors) {
            if (discovered[neighbor])
                continue

            parents[neighbor] = number
            commands[neighbor] = command
            if (neighbor == end)
                return parents to commands

            discovered[neighbor] = true
            queue.add(neighbor)
        }
    }

    return parents to commands
}

fun solve(edgeArray: Array<List<Pair<Int, Char>>>, start: Int, end: Int): List<Char> {
    val (parents, commands) = bfs(edgeArray, start, end)

    val result = LinkedList<Char>()
    var now = end

    while (now != start) {
        result.addFirst(commands[now])
        now = parents[now]
    }

    return result
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