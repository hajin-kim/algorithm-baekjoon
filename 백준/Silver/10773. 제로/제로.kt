import java.util.Stack

fun solve(n: Int, numbers: IntArray): Int {
    val stack = Stack<Int>()
    for (number in numbers) {
        if (number == 0)
            stack.pop()
        else
            stack.push(number)
    }
    return stack.sum()
}

fun main() {
    val n = readln().toInt()
    val numbers = IntArray(n) { readln().toInt() }

    val result = solve(n, numbers)

    println(result)
}