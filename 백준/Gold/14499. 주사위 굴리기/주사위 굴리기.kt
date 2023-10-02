data class Dice(
    var up: Int,
    var down: Int,
    var left: Int,
    var right: Int,
    var front: Int,
    var back: Int,
) {
    fun moveEast(): Dice = Dice(
        up = left,
        down = right,
        left = down,
        right = up,
        front = front,
        back = back,
    )

    private fun rotate(): Dice = Dice(
        up = up,
        down = down,
        left = front,
        right = back,
        front = right,
        back = left,
    )

    fun moveNorth() = rotate().moveEast().rotate().rotate().rotate()
    fun moveWest() = rotate().rotate().moveEast().rotate().rotate()
    fun moveSouth() = rotate().rotate().rotate().moveEast().rotate()
}

fun main() {
    var (n, m, x, y, k) = readln().split(" ").map { it.toInt() }
    val map = Array(n) {
        readln().split(" ").map { it.toInt() }.toIntArray()
    }
    val commands = readln().split(" ")

    var dice = Dice(0, 0, 0, 0, 0, 0)

    fun isInRange(x: Int, y: Int): Boolean = x in 0 until n && y in 0 until m

    val result = commands.mapNotNull { command ->
        val (nx, ny) = when (command) {
            "1" -> x to y + 1
            "2" -> x to y - 1
            "3" -> x - 1 to y
            "4" -> x + 1 to y
            else -> throw Exception()
        }

        if (!isInRange(nx, ny))
            return@mapNotNull null

        x = nx
        y = ny

        dice = when (command) {
            "1" -> dice.moveEast()
            "2" -> dice.moveWest()
            "3" -> dice.moveNorth()
            "4" -> dice.moveSouth()
            else -> throw Exception()
        }

        if (map[x][y] == 0) {
            map[x][y] = dice.down
        } else {
            dice.down = map[x][y]
            map[x][y] = 0
        }

        dice.up
    }

    println(result.joinToString("\n"))
}