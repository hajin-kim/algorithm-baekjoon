import java.util.*

fun main() {
    val n = readln().toInt()
    val students = readln().split(" ").map { it.toInt() }

    val sortedDeque: Deque<Int> = students.sorted().toCollection(LinkedList())
    val result = (0 until n).minOf { i ->
        sortedDeque.poll() + sortedDeque.pollLast()
    }

    println(result)
}