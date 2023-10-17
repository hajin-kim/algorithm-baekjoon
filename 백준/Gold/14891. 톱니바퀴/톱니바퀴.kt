fun main() {
    val nGears = 4
    val gears = Array(nGears) { readln().map { it.digitToInt() }.toIntArray() }
    val k = readln().toInt()
    val commands = List(k) {
        readln().split(" ").map { it.toInt() }.let { it[0] - 1 to it[1] }
    }

    // 1: right
    // 2: left
    fun previousDeltaToDirection(delta: Int): Int = when (delta) {
        1 -> 2
        -1 -> 6
        else -> throw IllegalArgumentException()
    }

    fun currentDeltaToDirection(delta: Int): Int = previousDeltaToDirection(-delta)

    // 1: clock wise
    // -1: ccw
    fun rotate(gearIndex: Int, rotation: Int) {
        val newGear = IntArray(8)
        for (now in 0 until 8) {
            val next = (now + rotation + 8) % 8
            newGear[next] = gears[gearIndex][now]
        }

        gears[gearIndex] = newGear
    }

    fun chainMove(previous: Int, delta: Int, previousRotation: Int) {
        val current = previous + delta
        if (current !in 0 until nGears)
            return

        val previousGear = gears[previous]
        val currentGear = gears[current]
        val previousDirection = previousDeltaToDirection(delta)
        val currentDirection = currentDeltaToDirection(delta)

        val currentRotation = -previousRotation

        if (previousGear[previousDirection] != currentGear[currentDirection]) {
            chainMove(current, delta, currentRotation)
            rotate(current, currentRotation)
        }
    }

    commands.forEach { (gear, rotation) ->
        chainMove(gear, 1, rotation)
        chainMove(gear, -1, rotation)
        rotate(gear, rotation)
    }

    val score = gears[0][0] * 1 + gears[1][0] * 2 + gears[2][0] * 4 + gears[3][0] * 8

    println(score)
}