import java.util.*

fun solve(n: Int): Int {
    val queue: Queue<Int> = ArrayDeque<Int>(n)
        .apply { addAll(1..n) }

    while (queue.size > 1) {
        queue.poll()
        val next = queue.poll()
        queue.add(next)
    }

    return queue.peek()
}

fun main() {
    val n = readln().toInt()

    val result = solve(n)

    println(result)
}