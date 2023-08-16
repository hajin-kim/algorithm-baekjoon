import java.util.*

fun monthDayToDayOfYear(month: Int, day: Int): Int {
    val daysInMonth = listOf(
        0,
        31,
        28,
        31,
        30,
        31,
        30,
        31,
        31,
        30,
        31,
        30,
    )

    return daysInMonth.take(month).sum() + day
}

val MARCH_FIRST = monthDayToDayOfYear(3, 1)
val NOVEMBER_THIRTY = monthDayToDayOfYear(11, 30)

fun main(args: Array<String>) {
    val n = readln().toInt()
    val flowers = (1..n).map {
        readln()
            .split(" ")
            .map { it.toInt() }
            .let { monthDayToDayOfYear(it[0], it[1]) to monthDayToDayOfYear(it[2], it[3]) }
    }
        .sortedBy { it.first }
        .toMutableList()

    var result = 0
    var requiredStartDay = MARCH_FIRST
    val pq = PriorityQueue<Int>(reverseOrder())
    while (requiredStartDay <= NOVEMBER_THIRTY) {
        while (flowers.isNotEmpty() && flowers.first().first <= requiredStartDay) {
            pq.add(flowers.removeFirst().second)
        }

        if (pq.isEmpty()) {
            println(0)
            return
        }

        requiredStartDay = pq.remove()
        pq.clear()
        ++result
    }

    println(result)
}