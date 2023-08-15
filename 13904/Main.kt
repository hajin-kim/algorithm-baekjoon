import java.util.*

fun main(args: Array<String>) {
    val n = readln().toInt()
    val tasks = (1..n)
        .map {
            readln().split(" ").map { it.toInt() }.let { it[0] to it[1] }
        }
        .sortedByDescending { it.first }

    var lastCheckedTime = tasks.first().first
    var result = 0
    val pq = PriorityQueue<Int>(compareByDescending { it })
    tasks.plus(0 to 0).forEach { (d, w) ->
        while (lastCheckedTime > d) {
            if (pq.isNotEmpty())
                result += pq.poll()
            --lastCheckedTime
        }

        pq.add(w)
    }

    println(result)
}