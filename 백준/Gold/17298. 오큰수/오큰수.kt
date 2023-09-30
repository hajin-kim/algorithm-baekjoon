import java.util.*

fun main() {
    val n = readln().toInt()
    val numbers = readln().split(" ").map { it.toInt() }

    val result = IntArray(n) { -1 }

    // index to value
    val stack = Stack<Pair<Int, Int>>()
    numbers.forEachIndexed { index, it ->
        while (stack.isNotEmpty() && stack.peek().second < it) {
            val (poppedIndex, _) = stack.pop()
            result[poppedIndex] = it
        }
        stack.push(index to it)
    }

    println(result.joinToString(" "))
}