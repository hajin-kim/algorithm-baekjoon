import java.util.*

fun main() {
    val n = readln().toInt()
    val numbers = (1..n).map { readln().toInt() }

    val priorityQueue = PriorityQueue(numbers)

    var result = 0L
    while (priorityQueue.size > 1) {
        val first = priorityQueue.poll()
        val second = priorityQueue.poll()
        val sum = first + second
        result += sum
        priorityQueue.add(sum)
    }

    println(result)
}