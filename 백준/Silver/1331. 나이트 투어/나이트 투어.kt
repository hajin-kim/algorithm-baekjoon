import kotlin.math.abs

fun isValidMovement(a: String, b: String): Boolean {
    val diff1 = abs(a[0] - b[0])
    val diff2 = abs(a[1] - b[1])
    return (diff1 == 1 && diff2 == 2) || (diff1 == 2 && diff2 == 1)
}

fun judge(positions: List<String>): Boolean {
    val visited = Array('F'.code + 1) { BooleanArray('6'.code + 1) }
    var visitedCount = 0
    positions
        .map { it.map { c -> c.code } }
        .forEach { (letter, number) ->
            if (visited[letter][number])
                return false

            visited[letter][number] = true
            visitedCount++
        }

    if (visitedCount != 36)
        return false

    positions
        .plus(positions.first())
        .zipWithNext { a, b ->
            if (!isValidMovement(a, b))
                return false
        }

    return true
}

fun main() {
    val positions = List(36) {
        readln()
    }

    val result = judge(positions)

    println(if (result) "Valid" else "Invalid")
}