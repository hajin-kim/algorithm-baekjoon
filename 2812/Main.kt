import java.util.*

fun main(args: Array<String>) {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val number = readln().map { it.digitToInt() }

    var nDeleted = 0
    val stack = Stack<Int>()
    val it = number.iterator()
    while (it.hasNext()) {
        val digit = it.next()
        while (stack.isNotEmpty() && stack.peek() < digit && nDeleted < k) {
            stack.pop()
            ++nDeleted
        }
        stack.push(digit)
    }
    repeat(k - nDeleted) {
        stack.pop()
    }
    println(stack.joinToString(""))
}