import java.util.*

fun bfs(): LongArray {
    val maxN = 100
    val defaultEdges = listOf(
        2 to listOf(1L),
        3 to listOf(7L),
        4 to listOf(4L),
        5 to listOf(2L),
        6 to listOf(0L, 6L),
        7 to listOf(8L),
    )
    val results = LongArray(maxN + 1)
    val pq = PriorityQueue<Pair<Int, Long>>(compareBy { it.second })

    defaultEdges.forEach { (usage, values) ->
        val minValue = values.filter { it != 0L }.min()
        results[usage] = minValue
        pq.add(usage to minValue)
    }

    fun getNextMin(value: Long, valueDiffs: List<Long>): Long {
        val valueString = value.toString()
        return valueDiffs
            .flatMap {
                valueString
                    .indices
                    .map { position ->
                        valueString.substring(0, position) + it + valueString.substring(position)
                    }
                    .plus(valueString + it)
            }
            .filter { it[0] != '0' }
            .minOf { it.toLong() }
    }

    while (pq.isNotEmpty()) {
        val (usage, value) = pq.poll()
        val toAdd = defaultEdges
            .map { (diffUsage, valueDiffs) ->
                val newUsage = usage + diffUsage
                newUsage to valueDiffs
            }
            .filter { (newUsage, _) -> newUsage <= maxN && results[newUsage] == 0L }
            .map { (newUsage, valueDiffs) ->
                val newValue = getNextMin(value, valueDiffs)

                results[newUsage] = newValue
                newUsage to newValue
            }
        pq.addAll(toAdd)
    }

    return results
}

fun main(args: Array<String>) {
    val smallests = bfs()

    val c = readln().toInt()
    repeat(c) {
        val n = readln().toInt()

        val biggest = if (n % 2 == 1) {
            "7" + "1".repeat((n - 3) / 2)
        } else {
            "1".repeat(n / 2)
        }

        val smallest = smallests[n]

        println("$smallest $biggest")
    }
}