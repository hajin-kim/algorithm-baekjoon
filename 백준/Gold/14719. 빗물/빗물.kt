import java.util.Stack

fun solve(rains: IntArray): Int {
    val stack = Stack<Int>()
    var result = 0

    for (i in rains.indices) {

        while (stack.size > 1 && rains[stack.peek()] < rains[i]) {
            val popped = stack.pop()
            val localLeftmost = stack.peek()

            val area = (i - localLeftmost - 1) * (minOf(rains[i], rains[localLeftmost]) - rains[popped])
            result += area
        }

        // set new left-most element
        if (stack.size == 1 && rains[stack.peek()] < rains[i]) {
            stack.pop()
        }

        stack.push(i)
    }

    return result
}

fun main() {
    val (h, w) = readln().split(" ").map { it.toInt() }
    val rains = readln().split(" ").map { it.toInt() }.toIntArray()

    val result = solve(rains)

    println(result)
}