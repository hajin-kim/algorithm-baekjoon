import java.util.*

fun main(args: Array<String>) {
    val n = readln().toInt()
    val lectures = (1..n)
        .map { readln().split(" ").map { it.toInt() }.let { it[0] to it[1] } }
        .sortedByDescending { it.second }

    if (n == 0) {
        print(0)
        return
    }

    var result = 0
    val pq = PriorityQueue<Int>(compareByDescending { it })

    var lastCheckedDate = lectures[0].second
    lectures.plus(0 to 0).forEach { (p, d) ->
        while (lastCheckedDate > d) {
            if (pq.isNotEmpty())
                result += pq.poll()
            --lastCheckedDate
        }

        pq.add(p)
    }

    println(result)
}