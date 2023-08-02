import java.util.*
import kotlin.math.max

// https://www.acmicpc.net/problem/1725

data class Histogram(val height: Int, var accumulated: Int = 1)

fun main(args: Array<String>) {
    val n = readln().toInt()
    val histograms = (1..n)
        .map {
            readln().toInt()
        }
        .plus(0)

    val stack = Stack<Histogram>()
    val maxArea = histograms.fold(0) { previousMaxArea, height ->
        var maxArea = previousMaxArea
        var accumulated = 0
        while (stack.isNotEmpty() && stack.peek().height >= height) {
            val popped = stack.pop()
            popped.accumulated += accumulated
            accumulated = popped.accumulated
            maxArea = max(maxArea, popped.height * popped.accumulated)
        }
        stack.push(Histogram(height, accumulated + 1))
        maxArea
    }
    println(maxArea)
}
