import java.util.*

fun main(args: Array<String>) {
    val nCourses = readln().toInt()

    // time, isStart, index
    val courses = (1..nCourses).map {
        readln()
            .split(" ")
            .map { it.toInt() }
    }
        .flatMap { listOf(Triple(it[1], true, it[0] - 1), Triple(it[2], false, it[0] - 1)) }
        .sortedWith(compareBy<Triple<Int, Boolean, Int>> { it.first }.thenBy { it.second })

    val lectureRooms = IntArray(nCourses)
    var result = 0

    val rooms: Queue<Int> = LinkedList()

    courses.forEach { (_, isStartTime, index) ->
        if (isStartTime) {
            if (rooms.isEmpty()) {
                rooms.add(++result)
            }
            lectureRooms[index] = rooms.poll()
        } else {
            rooms.add(lectureRooms[index])
        }
    }

    println(result)
    println(lectureRooms.joinToString("\n"))
}