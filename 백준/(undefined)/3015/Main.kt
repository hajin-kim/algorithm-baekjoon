import java.util.*

data class Audience(val height: Int, var sameHeightCounter: Int)

fun main(args: Array<String>) {
    val n = readln().toInt()
    val array = (1..n)
        .map { readln().toInt() }

    val stack = Stack<Audience>()
    var result = array.fold(0L) { acc, height ->
        var newAcc = acc
        var sameHeightCounter = 1
        while (stack.isNotEmpty() && stack.peek().height <= height) {
            val popped = stack.pop()
            // popped -> new element
            newAcc += popped.sameHeightCounter
            // collapse same elements
            if (popped.height == height) {
                sameHeightCounter += popped.sameHeightCounter
            }
            // stack peek <- popped
            if (stack.isNotEmpty()) {
                newAcc += 1
            }
        }
        stack.push(Audience(height, sameHeightCounter))

        newAcc
    }

    result += stack.count() - 1

    println(result)
}