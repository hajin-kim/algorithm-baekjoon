import java.util.*

fun main(args: Array<String>) {
    val n = readln().toInt()
    val heightChanges = (1..n)
        .map {
            readln().split(" ")[1].toInt()
        }
        .plus(0)

    val stack = Stack<Int>()
    var result = 0
    heightChanges.forEach {
        while (stack.isNotEmpty() && stack.peek() >= it) {
            val popped = stack.pop()
            if (popped > it)
                ++result
        }
        if (it > 0)
            stack.push(it)
    }

    println(result)
}
