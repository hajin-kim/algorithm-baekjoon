import java.util.PriorityQueue

fun main() {
    val (n, m, r) = readln().split(" ").map { it.toInt() }
    val ts = readln().split(" ").map { it.toInt() }.toIntArray()
    val roads = Array(r) {
        // a, b, l
        readln().split(" ").map { it.toInt() }.toIntArray()
    }

    val result = solve(n, m, ts, roads)

    println(result)
}

const val UNVISITED = 1_000_000_000

fun solve(n: Int, m: Int, ts: IntArray, roads: Array<IntArray>): Int {
    var result = 0

    repeat(n) { area ->
        var totalT = 0

        val distances = IntArray(n) { UNVISITED }
        val priorityQueue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })

        priorityQueue += area to 0

        while (priorityQueue.isNotEmpty()) {
            val (now, acc) = priorityQueue.poll()
            if (acc > m)
                break
            if (distances[now] != UNVISITED)
                continue
            distances[now] = acc

            totalT += ts[now]

            val neighbors = roads.filter { it[0] - 1 == now }.map { it[1] - 1 to acc + it[2] } +
                    roads.filter { it[1] - 1 == now }.map { it[0] - 1 to acc + it[2] }
            for (new in neighbors) {
                priorityQueue += new
            }
        }

        result = maxOf(totalT, result)
    }

    return result
}
