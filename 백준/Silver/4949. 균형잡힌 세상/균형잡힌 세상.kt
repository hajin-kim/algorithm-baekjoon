import java.util.Stack

fun solve(string: String): Boolean {
    val stack = Stack<Char>()

    for (char in string) {
        if (char in setOf('(', '['))
            stack.push(char)
        else if (char in setOf(')', ']')) {
            if (stack.isEmpty())
                return false

            val popped = stack.pop()

            if (
                (popped == '(') xor (char == ')') ||
                (popped == '[') xor (char == ']')
            )
                return false
        }
    }

    return stack.isEmpty()
}

fun main() {
    while (true) {
        val string = readln()
        if (string == ".")
            return

        val result = solve(string)

        println(if (result) "yes" else "no")
    }
}