const val UP = 0
const val DOWN = 1
const val FRONT = 2
const val BACK = 3
const val LEFT = 4
const val RIGHT = 5


data class Cube(
    val state: Array<Array<CharArray>>,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Cube) return false

        if (!state.contentDeepEquals(other.state)) return false

        return true
    }

    override fun hashCode(): Int {
        return state.contentDeepHashCode()
    }

    fun rotate(command: String): Cube {
        val axis = when (command[0]) {
            'U' -> UP
            'D' -> DOWN
            'F' -> FRONT
            'B' -> BACK
            'L' -> LEFT
            'R' -> RIGHT
            else -> throw Exception()
        }
        val direction = command[1]

        val newState = state.map { it.map { inner -> inner.clone() }.toTypedArray() }.toTypedArray()

        fun Array<CharArray>.rotateClockwise(): Array<CharArray> {
            val result = Array(3) { CharArray(3) }
            for (y in 0..2) {
                for (x in 0..2) {
                    result[y][x] = this[x][2 - y]
                }
            }
            return result
        }

        fun Array<CharArray>.rotateCounterClockwise(): Array<CharArray> {
            val result = Array(3) { CharArray(3) }
            for (y in 0..2) {
                for (x in 0..2) {
                    result[y][x] = this[2 - x][y]
                }
            }
            return result
        }

        newState[axis] = when (direction) {
            '+' -> state[axis].rotateClockwise()
            '-' -> state[axis].rotateCounterClockwise()
            else -> throw Exception()
        }

        when (axis) {
            UP -> {
                if (direction == '+') {
                    for (x in 0..2) {
                        newState[LEFT][2][x] = state[FRONT][2][x]
                        newState[BACK][2][x] = state[LEFT][2][x]
                        newState[RIGHT][2][x] = state[BACK][2][x]
                        newState[FRONT][2][x] = state[RIGHT][2][x]
                    }
                } else {
                    for (x in 0..2) {
                        newState[FRONT][2][x] = state[LEFT][2][x]
                        newState[LEFT][2][x] = state[BACK][2][x]
                        newState[BACK][2][x] = state[RIGHT][2][x]
                        newState[RIGHT][2][x] = state[FRONT][2][x]
                    }
                }
            }

            DOWN -> {
                if (direction == '+') {
                    for (x in 0..2) {
                        newState[FRONT][0][x] = state[LEFT][0][x]
                        newState[LEFT][0][x] = state[BACK][0][x]
                        newState[BACK][0][x] = state[RIGHT][0][x]
                        newState[RIGHT][0][x] = state[FRONT][0][x]
                    }
                } else {
                    for (x in 0..2) {
                        newState[LEFT][0][x] = state[FRONT][0][x]
                        newState[BACK][0][x] = state[LEFT][0][x]
                        newState[RIGHT][0][x] = state[BACK][0][x]
                        newState[FRONT][0][x] = state[RIGHT][0][x]
                    }
                }
            }

            FRONT -> {
                if (direction == '+') {
                    for (i in 0..2) {
                        newState[UP][0][i] = state[LEFT][i][2]
                        newState[RIGHT][2 - i][0] = state[UP][0][i]
                        newState[DOWN][0][i] = state[RIGHT][2 - i][0]
                        newState[LEFT][i][2] = state[DOWN][0][i]
                    }
                } else {
                    for (i in 0..2) {
                        newState[LEFT][i][2] = state[UP][0][i]
                        newState[UP][0][i] = state[RIGHT][2 - i][0]
                        newState[RIGHT][2 - i][0] = state[DOWN][0][i]
                        newState[DOWN][0][i] = state[LEFT][i][2]
                    }
                }
            }

            BACK -> {
                if (direction == '+') {
                    for (i in 0..2) {
                        newState[LEFT][i][0] = state[UP][2][i]
                        newState[UP][2][i] = state[RIGHT][2 - i][2]
                        newState[RIGHT][2 - i][2] = state[DOWN][2][i]
                        newState[DOWN][2][i] = state[LEFT][i][0]
                    }
                } else {
                    for (i in 0..2) {
                        newState[UP][2][i] = state[LEFT][i][0]
                        newState[RIGHT][2 - i][2] = state[UP][2][i]
                        newState[DOWN][2][i] = state[RIGHT][2 - i][2]
                        newState[LEFT][i][0] = state[DOWN][2][i]
                    }
                }
            }

            LEFT -> {
                if (direction == '+') {
                    for (i in 0..2) {
                        newState[UP][i][0] = state[BACK][2 - i][2]
                        newState[FRONT][i][0] = state[UP][i][0]
                        newState[DOWN][2 - i][2] = state[FRONT][i][0]
                        newState[BACK][2 - i][2] = state[DOWN][2 - i][2]
                    }
                } else {
                    for (i in 0..2) {
                        newState[BACK][2 - i][2] = state[UP][i][0]
                        newState[UP][i][0] = state[FRONT][i][0]
                        newState[FRONT][i][0] = state[DOWN][2 - i][2]
                        newState[DOWN][2 - i][2] = state[BACK][2 - i][2]
                    }
                }
            }

            RIGHT -> {
                if (direction == '+') {
                    for (i in 0..2) {
                        newState[BACK][2 - i][0] = state[UP][i][2]
                        newState[UP][i][2] = state[FRONT][i][2]
                        newState[FRONT][i][2] = state[DOWN][2 - i][0]
                        newState[DOWN][2 - i][0] = state[BACK][2 - i][0]
                    }
                } else {
                    for (i in 0..2) {
                        newState[UP][i][2] = state[BACK][2 - i][0]
                        newState[FRONT][i][2] = state[UP][i][2]
                        newState[DOWN][2 - i][0] = state[FRONT][i][2]
                        newState[BACK][2 - i][0] = state[DOWN][2 - i][0]
                    }
                }
            }
        }

        return Cube(newState)
    }
}

fun main() {
    val c = readln().toInt()
    repeat(c) {
        val n = readln().toInt()
        val commands = readln().split(" ")

        val initial = Cube(
            arrayOf(
                // up
                arrayOf(
                    // on the left <--> right
                    charArrayOf('w', 'w', 'w'), // on the back
                    charArrayOf('w', 'w', 'w'),
                    charArrayOf('w', 'w', 'w'), // on the front
                ),
                // down
                arrayOf(
                    charArrayOf('y', 'y', 'y'),
                    charArrayOf('y', 'y', 'y'),
                    charArrayOf('y', 'y', 'y'),
                ),
                // front
                arrayOf(
                    charArrayOf('r', 'r', 'r'),
                    charArrayOf('r', 'r', 'r'),
                    charArrayOf('r', 'r', 'r'),
                ),
                // back
                arrayOf(
                    charArrayOf('o', 'o', 'o'),
                    charArrayOf('o', 'o', 'o'),
                    charArrayOf('o', 'o', 'o'),
                ),
                // left
                arrayOf(
                    charArrayOf('g', 'g', 'g'),
                    charArrayOf('g', 'g', 'g'),
                    charArrayOf('g', 'g', 'g'),
                ),
                // right
                arrayOf(
                    charArrayOf('b', 'b', 'b'),
                    charArrayOf('b', 'b', 'b'),
                    charArrayOf('b', 'b', 'b'),
                ),
            )
        )

        val result = commands.fold(initial) { acc, command ->
            acc.rotate(command)
        }
        val upsideFace = result.state[UP].reversed().joinToString("\n") { it.joinToString("") }
        println(upsideFace)
    }
}