import java.util.*

fun main(args: Array<String>) {
    repeat(readln().toInt()) {
        val commands = readln()
        val n = readln().toInt()
        val numbers: Deque<Int> = readln()
            .drop(1)
            .dropLast(1)
            .split(",")
            .mapNotNull { it.toIntOrNull() }
            .toCollection(LinkedList())

        var reversed = false
        var errorOccurred = false

        commands.forEach { command ->
            if (command == 'R')
                reversed = !reversed
            else if (numbers.isEmpty()) {
                errorOccurred = true
                return@forEach
            } else if (reversed)
                numbers.removeLast()
            else
                numbers.removeFirst()

        }

        if (errorOccurred)
            println("error")
        else {
            val resultArray = if (reversed) numbers.reversed() else numbers
            println(resultArray.joinToString(",", prefix = "[", postfix = "]"))
        }
    }
}
