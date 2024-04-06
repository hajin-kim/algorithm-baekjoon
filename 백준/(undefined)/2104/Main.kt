import java.util.*

data class Range(val minElement: Int, val previousAccumulatedSum: Long)

fun main(args: Array<String>) {
    val n = readln().toInt()
    val array = readln().split(" ").map { it.toInt() }.plus(0)

    val stack = Stack<Range>()
    var accumulatedSum = 0L
    val maxScore = array.fold(0L) { previousMaxScore, element ->
        var maxScore = previousMaxScore

        var collapsedAccumulatedSum = accumulatedSum
        while (stack.isNotEmpty() && stack.peek().minElement >= element) {
            val popped = stack.pop()
            collapsedAccumulatedSum = popped.previousAccumulatedSum
            val score = popped.minElement * (accumulatedSum - popped.previousAccumulatedSum)
            maxScore = maxOf(maxScore, score)
        }
        stack.push(Range(element, collapsedAccumulatedSum))
        accumulatedSum += element

        maxScore
    }
    println(maxScore)
}
