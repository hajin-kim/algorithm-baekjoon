import java.util.*
import kotlin.math.max

fun main(args: Array<String>) {
    val n = readln().toInt()
    val array = readln().split(" ").map { it.toInt() }.toIntArray()

    val maxCounts = IntArray(n)
    array.forEachIndexed { index, now ->
        var lengthLessThanIt = 0
        (0 until index).forEach {
            if (array[it] < now) {
                lengthLessThanIt = max(lengthLessThanIt, maxCounts[it])
            }
        }
        maxCounts[index] = lengthLessThanIt + 1
    }

    val maxCount = maxCounts.max()
    val example = LinkedList<Int>()
    var currentMaxCount = maxCount
    maxCounts.withIndex().reversed().forEach { (index, value) ->
        if (value == currentMaxCount) {
            example.addFirst(array[index])
            currentMaxCount--
        }
    }

    println(maxCount)
    println(example.joinToString(" "))
}
