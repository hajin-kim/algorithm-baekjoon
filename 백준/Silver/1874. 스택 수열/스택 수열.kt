import java.util.*

fun solve(sequence: List<Int>): MutableList<Boolean>? {
    val stack = Stack<Int>()
    var now = 1

    val results = mutableListOf<Boolean>()

    for (number in sequence) {
        while (now <= number) {
            stack.push(now)
            results.add(true)
            ++now
        }
        var poppedNumber = false
        while (stack.isNotEmpty() && stack.peek() >= number) {
            if (stack.peek() == number)
                poppedNumber = true
            stack.pop()
            results.add(false)
        }
        if (!poppedNumber)
            return null
    }

    return results
}

fun main() {
    val n = readln().toInt()
    val sequence = List(n) {
        readln().toInt()
    }

    val result = solve(sequence)

    if (result == null)
        println("NO")
    else
        println(result.joinToString("\n") { if (it) "+" else "-" })
}