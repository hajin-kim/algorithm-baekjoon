enum class Direction {
    LEFT,
    RIGHT,
    UP,
    DOWN,
    ;

    fun onSlash(): Direction = when (this) {
        LEFT -> DOWN
        DOWN -> LEFT
        RIGHT -> UP
        UP -> RIGHT
    }

    fun onBackslash(): Direction = when (this) {
        LEFT -> UP
        UP -> LEFT
        RIGHT -> DOWN
        DOWN -> RIGHT
    }

    fun getDrDc(): Pair<Int, Int> = when (this) {
        LEFT -> 0 to -1
        UP -> -1 to 0
        RIGHT -> 0 to 1
        DOWN -> 1 to 0
    }

    fun getResultChar(): String = when (this) {
        LEFT -> "L"
        UP -> "U"
        RIGHT -> "R"
        DOWN -> "D"
    }
}

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val space = Array(n) {
        readln().toCharArray()
    }
    val (pr, pc) = readln().split(" ").map { it.toInt() }

    val (direction, timeOrNull) = solve(n, m, space, pr, pc)

    println(direction.getResultChar())
    println(timeOrNull ?: "Voyager")
}

fun solve(n: Int, m: Int, space: Array<CharArray>, pr: Int, pc: Int): Pair<Direction, Int?> {
    var resultDirection = Direction.UP
    var resultTime = -1

    for (initialDirection in listOf(
        Direction.UP,
        Direction.RIGHT,
        Direction.DOWN,
        Direction.LEFT,
    )) {
        var direction = initialDirection
        var time = 0
        val visited = Array(4) { Array(n) { BooleanArray(m) } }

        var r = pr - 1
        var c = pc - 1

        while (true) {
            if (r !in 0 until n || c !in 0 until m)
                break
            val now = space[r][c]
            if (now == 'C')
                break

            if (visited[direction.ordinal][r][c])
                return initialDirection to null
            visited[direction.ordinal][r][c] = true

            if (now == '/')
                direction = direction.onSlash()
            else if (now == '\\')
                direction = direction.onBackslash()

            time += 1

            val (dr, dc) = direction.getDrDc()
            r = r + dr
            c = c + dc
        }

        if (time > resultTime) {
            resultDirection = initialDirection
            resultTime = time
        }
    }

    return resultDirection to resultTime
}
