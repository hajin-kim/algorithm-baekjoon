import java.util.*
import kotlin.math.max

data class MinPay(val pay: Long, val count: Int)

fun main(args: Array<String>) {
    val n = readln().toInt()
    val pays = readln().split(" ").map { it.toLong() }.plus(0L)

    val stack = Stack<MinPay>()
    val result = pays.fold(0L) { acc, pay ->
        var newAcc = acc
        var selfIncludedCount = 0

        while (stack.isNotEmpty() && stack.peek().pay >= pay) {
            val popped = stack.pop()
            selfIncludedCount += popped.count
            newAcc = max(newAcc, popped.pay * selfIncludedCount)
        }
        stack.push(MinPay(pay, selfIncludedCount + 1))

        newAcc
    }

    println(result)
}