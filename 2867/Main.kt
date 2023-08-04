import java.util.*

data class Range(val number: Long, val leftCount: Long = 0)

fun calculatedResultOfAffectedRangesByPopped(popped: Range, rightCount: Long): Long {
    return popped.number * ((popped.leftCount + 1) * (rightCount + 1) - 1)
}

fun Stack<Range>.popUntil(new: Long?, isAscendingOrdered: Boolean): Long {
    var result = 0L

    var rightCount = 0L
    while (this.isNotEmpty() && (
                new == null || // pop all
                        if (isAscendingOrdered) this.peek().number >= new
                        else this.peek().number <= new)
    ) {
        val popped = this.pop()
        result += calculatedResultOfAffectedRangesByPopped(popped, rightCount)
        rightCount += popped.leftCount + 1
    }
    new?.let { this.push(Range(it, rightCount)) }

    return result
}

fun main(args: Array<String>) {
    val n = readln().toInt()
    val sequence = (1..n).map {
        readln().toLong()
    }

    val minStack: Stack<Range> = Stack()
    val maxStack: Stack<Range> = Stack()

    var result = sequence.fold(0L) { acc, new ->
        var result = acc

        result -= minStack.popUntil(new, true)
        result += maxStack.popUntil(new, false)

        result
    }

    result -= minStack.popUntil(null, true)
    result += maxStack.popUntil(null, false)

    println(result)
}