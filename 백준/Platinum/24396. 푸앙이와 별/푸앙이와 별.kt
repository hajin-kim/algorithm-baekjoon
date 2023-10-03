import java.util.*

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val cut = (1..m).flatMap {
        readln()
            .split(" ")
            .map { it.toInt() }
            .let { listOf(it[0] to it[1], it[1] to it[0]) }
    }

    val blocked = cut
        .groupBy { it.first }
        .mapValues { (_, v) -> v.map { it.second }.toHashSet() }

    val undiscovered = hashSetOf(*Array(n - 1) { it + 2 })
    var toVisit: Queue<Int> = LinkedList<Int>()
        .apply { add(1) }
    val distances = IntArray(n + 1) { -1 }
        .apply { this[1] = 0 }

    var distance = 1
    while (toVisit.isNotEmpty()) {
        val nextToVisit: Queue<Int> = LinkedList()

        while (toVisit.isNotEmpty()) {
            val now = toVisit.poll()

            val iUnvisited = undiscovered.iterator()
            while (iUnvisited.hasNext()) {
                val candidate = iUnvisited.next()
                if (blocked[now] == null || candidate !in blocked[now]!!) {
                    distances[candidate] = distance
                    nextToVisit.add(candidate)
                    iUnvisited.remove()
                }
            }
        }

        ++distance
        toVisit = nextToVisit
    }

    val result = distances.drop(1).joinToString("\n")
    println(result)
}