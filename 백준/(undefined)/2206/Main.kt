import java.util.*

data class Position(
    val y: Int,
    val x: Int,
    val normalMin: Int,
    val brokenMin: Int,
)

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val isWall = Array(n) { readln().map { it == '1' }.toBooleanArray() }

    val INVALID = -1
    val queue: Queue<Position> = LinkedList()
    val normalMins = Array(n) { IntArray(m) { INVALID } }
    val brokenMins = Array(n) { IntArray(m) { INVALID } }

    queue.add(Position(0, 0, 0, INVALID))

    while (queue.isNotEmpty()) {
        val (y, x, normalMin, brokenMin) = queue.poll()
        if (y !in 0 until n || x !in 0 until m)
            continue

        if (normalMin == INVALID && brokenMin == INVALID)
            continue

        val currentNormalMin =
            if (isWall[y][x] || normalMin == INVALID) INVALID
            else normalMin + 1

        val currentBrokenMin = if (isWall[y][x]) {
            if (normalMin == INVALID) INVALID
            else normalMin + 1
        } else {
            if (brokenMin == INVALID) INVALID
            else brokenMin + 1
        }

        var shouldRun = false
        if (currentNormalMin != INVALID && (normalMins[y][x] == INVALID || normalMins[y][x] > currentNormalMin)
        ) {
            normalMins[y][x] = currentNormalMin
            shouldRun = true
        }
        if (currentBrokenMin != INVALID && (brokenMins[y][x] == INVALID || brokenMins[y][x] > currentBrokenMin)) {
            brokenMins[y][x] = currentBrokenMin
            shouldRun = true
        }

        if (!shouldRun)
            continue

        listOf(
            y - 1 to x,
            y + 1 to x,
            y to x - 1,
            y to x + 1,
        ).forEach { (ny, nx) ->
            queue.add(Position(ny, nx, currentNormalMin, currentBrokenMin))
        }
    }

    if (brokenMins[n - 1][m - 1] == INVALID)
        if (normalMins[n - 1][m - 1] == INVALID)
            println(-1)
        else
            println(normalMins[n - 1][m - 1])
    else
        println(brokenMins[n - 1][m - 1])
}