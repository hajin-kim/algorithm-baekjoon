import java.util.*
import kotlin.math.max

data class Building(val height: Int, var rightCount: Int)

fun main(args: Array<String>) {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val buildings = readln().split(" ").map { it.toInt() }
    val towers = readln().split(" ").map { it.toInt() }

    val maxMap = sortedMapOf<Int, Int>()

    val stack = Stack<Building>()
    buildings.plus(Integer.MAX_VALUE).forEach { building ->
        var count = 0
        while (stack.isNotEmpty() && stack.peek().height < building) {
            val popped = stack.pop()
            ++count
            maxMap[popped.height] = popped.rightCount
            if (stack.isNotEmpty())
                stack.peek().rightCount = max(popped.rightCount + 1, stack.peek().rightCount)
        }
        stack.push(Building(building, 1))
    }

    val sortedHeightToCount = maxMap
        .entries
        .scan(0 to 0) { acc, entry ->
            entry.key to max(acc.second, entry.value)
        }
        .toTypedArray()

    val result = towers.map { tower ->
        val index = sortedHeightToCount.binarySearch(tower to 0, compareBy { it.first })
        if (index < 0) {
            val insertionPoint = -index - 1
            sortedHeightToCount[insertionPoint - 1].second
        } else {
            sortedHeightToCount[index].second
        }
    }

    println(result.joinToString(" "))
}
