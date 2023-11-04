import java.util.*

fun solve(buildings: List<Int>): Long {
    val stack = Stack<Int>()

    var result = 0L

    @Suppress("NAME_SHADOWING")
    val buildings = buildings
        .plus(Int.MAX_VALUE) // pop remaining all
        .toIntArray()

    for (i in buildings.indices) {
        while (stack.isNotEmpty() && buildings[stack.peek()] <= buildings[i]) {
            result += (i - stack.pop() - 1)
        }
        stack.push(i)
    }

    return result
}

fun main() {
    val n = readln().toInt()
    val buildings = List(n) {
        readln().toInt()
    }

    val result = solve(buildings)

    println(result)
}