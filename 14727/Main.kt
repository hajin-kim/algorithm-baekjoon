import java.util.*
import kotlin.math.max

data class Histogram(val pay: Long, val count: Int)

fun main(args: Array<String>) {
    val n = readln().toInt()
    val histograms = (1..n).map { readln().toLong() }.plus(0L)

    val stack = Stack<Histogram>()
    val result = histograms.fold(0L) { acc, pay ->
        var newAcc = acc
        var selfIncludedCount = 0

        while (stack.isNotEmpty() && stack.peek().pay >= pay) {
            val popped = stack.pop()
            selfIncludedCount += popped.count
            newAcc = max(newAcc, popped.pay * selfIncludedCount)
        }
        stack.push(Histogram(pay, selfIncludedCount + 1))

        newAcc
    }

    println(result)
}