import java.util.*

data class Record(val min: Long, val start: Int)
data class Score(val score: Long, val startPlusOne: Int, val endInclusivePlusOne: Int)

fun main(args: Array<String>) {
    val n = readln().toInt()
    val values = readln()
        .split(" ")
        .map { it.toLong() }
        .plus(0)

    val prefixSum = values.scan(0L) { acc, value -> acc + value }

    fun querySum(start: Int, endInclusive: Int): Long {
        return prefixSum[endInclusive + 1] - prefixSum[start]
    }

    var maxScore = Score(0, 1, 1) // when values = {0}
    val stack = Stack<Record>()
    values.forEachIndexed { index, value ->
        var affectedRangeStart = index
        while (stack.isNotEmpty() && stack.peek().min >= value) {
            val popped = stack.pop()
            affectedRangeStart = popped.start
            val score = popped.min * querySum(popped.start, index - 1)
            if (score > maxScore.score)
                maxScore = Score(score, popped.start + 1, index)
        }
        stack.push(Record(value, affectedRangeStart))
    }

    println(maxScore.score)
    println("${maxScore.startPlusOne} ${maxScore.endInclusivePlusOne}")
}