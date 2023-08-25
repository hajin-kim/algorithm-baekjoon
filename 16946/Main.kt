import java.util.*

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val isMovable = Array(n) { readln().map { it == '0' }.toBooleanArray() }

    val visitedAt = Array(n) { IntArray(m) }
    val areaByAt = IntArray(n * m + 1)

    fun isInRange(y: Int, x: Int) = y in 0 until n && x in 0 until m

    (0 until n).forEach { startY ->
        (0 until m).forEach { startX ->
            val now = startY * m + startX + 1

            val stack = Stack<Pair<Int, Int>>()
            stack.push(startY to startX)

            while (stack.isNotEmpty()) {
                val (y, x) = stack.pop()

                if (!isInRange(y, x) || visitedAt[y][x] != 0 || !isMovable[y][x])
                    continue

                visitedAt[y][x] = now
                ++areaByAt[now]

                stack.push(y + 1 to x)
                stack.push(y - 1 to x)
                stack.push(y to x + 1)
                stack.push(y to x - 1)
            }
        }
    }

    val result = StringBuilder()
    (0 until n).forEach { startY ->
        (0 until m).forEach { startX ->
            if (isMovable[startY][startX])
                result.append('0')
            else {
                val resultHere = listOf(
                    startY + 1 to startX,
                    startY - 1 to startX,
                    startY to startX + 1,
                    startY to startX - 1,
                )
                    .filter { (y, x) -> isInRange(y, x) }
                    .map { (y, x) -> visitedAt[y][x] }
                    .toSet()
                    .sumOf { areaByAt[it] }
                    .plus(1)
                    .rem(10)
                result.append(resultHere)
            }
        }
        result.append('\n')
    }

    println(result)
}