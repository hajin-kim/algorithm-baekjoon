import java.util.*

data class Building(val id: Int, val height: Int)

fun main(args: Array<String>) {
    val n = readln().toInt()
    val buildings = readln()
        .split(" ")
        .mapIndexed { index, s -> Building(index + 1, s.toInt()) }

    val results = mutableListOf<Int>()
    val stack = Stack<Building>()

    buildings.forEach {
        var result = 0
        while (stack.isNotEmpty()) {
            // no case where stack.peek().height == d.height
            if (stack.peek().height <= it.height) {
                stack.pop()
            } else {
                result = stack.peek().id
                break
            }
        }
        stack.push(it)
        results.add(result)
    }

    println(results.joinToString(" "))
}
