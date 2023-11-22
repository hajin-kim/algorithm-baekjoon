import java.util.ArrayDeque
import java.util.Queue

const val UNREACHABLE = -1

fun solve(n: Int, times: IntArray, prerequisiteLists: Array<List<Int>>): IntArray {
    val neighbors = Array(n) { mutableListOf<Int>() }
    for (i in 0 until n) {
        for (prerequisite in prerequisiteLists[i])
            neighbors[prerequisite].add(i)
    }

    val requiredTimes = IntArray(n) { UNREACHABLE }
    val prerequisiteCounts = IntArray(n)
    val queue: Queue<Int> = ArrayDeque(n)

    for (i in 0 until n) {
        prerequisiteCounts[i] = prerequisiteLists[i].size
        if (prerequisiteLists[i].isEmpty())
            queue.add(i)
    }

    while (queue.isNotEmpty()) {
        val now = queue.poll()

        val maxPrerequisiteTime = prerequisiteLists[now].maxOfOrNull { requiredTimes[it] } ?: 0
        requiredTimes[now] = times[now] + maxPrerequisiteTime

        for (neighbor in neighbors[now]) {
            --prerequisiteCounts[neighbor]
            if (prerequisiteCounts[neighbor] > 0)
                continue
            
            queue.add(neighbor)
        }
    }

    return requiredTimes
}

fun main() {
    val n = readln().toInt()

    val (times, prerequisiteLists) = Array(n) {
        val ints = readln().split(" ").map { it.toInt() }
        val time = ints.first()
        val prerequisites = ints.drop(1).dropLast(1).map { it - 1 }

        time to prerequisites
    }.unzip()

    val result = solve(n, times.toIntArray(), prerequisiteLists.toTypedArray())

    println(result.joinToString("\n"))
}