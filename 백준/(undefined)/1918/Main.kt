import java.util.*

val stack = Stack<Char>()

var parenthesis = 0

val operatorStack = Stack<Pair<Char, Int>>()

fun popOperatorStack(until: Int = 0) {
    while (operatorStack.isNotEmpty() && operatorStack.peek().second >= until) {
        stack.push(operatorStack.pop().first)
    }
}

fun getCurrentPriority(operator: Char): Int {
    return when (operator) {
        '+' -> 1
        '-' -> 1
        '*' -> 2
        '/' -> 2
        else -> 0
    } + parenthesis * 10
}

fun main(args: Array<String>) {
    val chars = readln()
    chars.forEach {
        if (it.isLetter()) {
            stack.push(it)
            return@forEach
        }

        when (it) {
            '(' -> ++parenthesis
            ')' -> {
                --parenthesis
            }

            else -> {
                val currentPriority = getCurrentPriority(it)
                popOperatorStack(until = currentPriority)
                operatorStack.push(it to currentPriority)
            }
        }
    }

    popOperatorStack()

    println(stack.joinToString("") { it.toString() })
}
