import java.util.*
import kotlin.math.max

data class Histogram(val height: Int, var accumulated: Long = 1L)

fun main(args: Array<String>) {
    while (true) {
        val inputs = readln().split(" ").map { it.toInt() }
        val n = inputs[0]

        if (n == 0) break

        val histograms = inputs.drop(1)
            .plus(0)

        val stack = Stack<Histogram>()
        val maxArea = histograms.fold(0L) { previousMaxArea, height ->
            var maxArea = previousMaxArea
            var accumulated = 0L
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
}
