import java.util.*

fun main(args: Array<String>) {
    val n = readln().toInt()
    val circles = (1..n)
        .map { readln().split(" ").map { it.toInt() }.let { it[0] - it[1] to it[0] + it[1] } }
        .sortedWith(compareBy<Pair<Int, Int>> { it.first }.thenByDescending { it.second })

    // start, end, isContinuous
    val stack = Stack<Triple<Int, Int, Boolean>>()
    var count = n + 1
    circles.forEach {
        var isContinuous = false

        if (stack.isNotEmpty() && stack.peek().first == it.first)
            isContinuous = true // nothing is popped

        while (stack.isNotEmpty() && stack.peek().second <= it.first) {
            val popped = stack.pop()
            isContinuous = popped.third && popped.second == it.first
            if (isContinuous && stack.isNotEmpty() && stack.peek().second == it.second)
                ++count
        }

        stack.push(Triple(it.first, it.second, isContinuous))
    }

    println(count)
}