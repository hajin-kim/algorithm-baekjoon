import java.util.*
import kotlin.math.max

fun main(args: Array<String>) {
    val nCourses = readln().toInt()

    val priorityQueue = PriorityQueue(
        nCourses * 2,
        compareBy<Pair<Int, Boolean>> { it.first }.thenBy { it.second }
    )
    repeat(nCourses) {
        priorityQueue.addAll(readln()
            .split(" ")
            .map { it.toInt() }
            .let { listOf(it[1] to true, it[2] to false) })
    }

    var currentRooms = 0
    var result = 0

    while (priorityQueue.isNotEmpty()) {
        val (_, isStartTime) = priorityQueue.poll()
        if (isStartTime) {
            currentRooms++
            result = max(result, currentRooms)
        } else {
            currentRooms--
        }
    }

    println(result)
}