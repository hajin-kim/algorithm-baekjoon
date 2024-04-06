import java.util.*

data class Range(val minElement: Int, val start: Int, val endInclusive: Int)

fun main(args: Array<String>) {
    val n = readln().toInt()
    val array = readln().split(" ").map { it.toInt() }.plus(0)

    val accumulatedSum = array
        .fold(mutableListOf(0L)) { acc, element ->
            acc.apply { add(acc.last() + element) }
        }
        .toLongArray()

    fun partialSum(start: Int, endInclusive: Int): Long {
        return accumulatedSum[endInclusive + 1] - accumulatedSum[start]
    }

    val stack = Stack<Range>()
    val maxScore = array.foldIndexed(0L) { index, previousMaxScore, element ->
        var maxScore = previousMaxScore

        var collapsedStartIndex = index
        while (stack.isNotEmpty() && stack.peek().minElement >= element) {
            val popped = stack.pop()
            collapsedStartIndex = popped.start
            val score = popped.minElement * partialSum(popped.start, index - 1)
            maxScore = maxOf(maxScore, score)
        }
        stack.push(Range(element, collapsedStartIndex, index))

        maxScore
    }
    println(maxScore)
}