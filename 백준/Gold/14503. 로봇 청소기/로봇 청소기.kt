fun move(y: Int, x: Int, direction: Int): Pair<Int, Int> {
    val nextY = y + when (direction) {
        0 -> -1
        1 -> 0
        2 -> 1
        3 -> 0
        else -> throw IllegalStateException()
    }
    val nextX = x + when (direction) {
        0 -> 0
        1 -> 1
        2 -> 0
        3 -> -1
        else -> throw IllegalStateException()
    }
    return nextY to nextX
}

fun moveBack(y: Int, x: Int, direction: Int): Pair<Int, Int> {
    val nextY = y + when (direction) {
        0 -> 1
        1 -> 0
        2 -> -1
        3 -> 0
        else -> throw IllegalStateException()
    }
    val nextX = x + when (direction) {
        0 -> 0
        1 -> -1
        2 -> 0
        3 -> 1
        else -> throw IllegalStateException()
    }
    return nextY to nextX
}

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    var (y, x, direction) = readln().split(" ").map { it.toInt() }
    val isWall = Array(n) { readln().split(" ").map { it[0] == '1' }.toBooleanArray() }

    val isClean = Array(n) { BooleanArray(m) }
    var result = 0

    while (true) {
        if (y !in 0 until n || x !in 0 until m || isWall[y][x])
            break

        if (!isClean[y][x]) {
            isClean[y][x] = true
            result++
        }

        val nextDirectionOrNull = listOf(1, 2, 3, 0)
            .map { (direction - it + 4) % 4 }
            .firstOrNull {
                val (nextY, nextX) = move(y, x, it)
                !isWall[nextY][nextX] && !isClean[nextY][nextX]
            }

        if (nextDirectionOrNull == null) {
            val (nextY, nextX) = moveBack(y, x, direction)
            y = nextY
            x = nextX
        } else {
            direction = nextDirectionOrNull
            val (nextY, nextX) = move(y, x, direction)
            y = nextY
            x = nextX
        }
    }

    println(result)
}