import java.util.*
import kotlin.math.max

fun main(args: Array<String>) {
    val n = readln().toInt()

    val values = (1..n).map { readln().toInt() }

    val stack = Stack<Int>()
    val result = values
        .plus(values.max()) // to pop all remaining elements in stack at the end
        .fold(0L) { acc, it ->
            var nOperations = 0
            while (stack.isNotEmpty() && stack.peek() <= it) {
                nOperations = max(nOperations, it - stack.peek())
                stack.pop()
            }
            stack.push(it)

            acc + nOperations
        }

    println(result)
}
