import java.util.PriorityQueue

fun solve(n: Int): Long {
    val priorityQueue = PriorityQueue<Long>()
    priorityQueue += (0L..9L)

    var i = 0

    while (priorityQueue.isNotEmpty() && i < n) {
        val now = priorityQueue.poll().toString()

        val candidates = ('0'..'9')
            .filter { it > now[0] }
            .map { (it + now).toLong() }
        priorityQueue += candidates

        ++i
    }

    return if (priorityQueue.isNotEmpty()) priorityQueue.peek() else -1L
}

fun main() {
    val n = readln().toInt()

    val result = solve(n)

    println(result)
}