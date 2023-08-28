import java.util.*

data class Position(val y: Int, val x: Int) {
    private fun isInRange(map: Array<CharArray>) = y in map.indices && x in map[0].indices
    private fun isOnWall(map: Array<CharArray>) = map[y][x] == '#'

    fun getNextOrNull(dy: Int, dx: Int, map: Array<CharArray>): Position? {
        val next = Position(y + dy, x + dx)
        return next.takeIf { (it.isInRange(map) && !it.isOnWall(map)) }
    }
}

data class State(val redPosition: Position, val bluePosition: Position) {
    fun listNextStates(map: Array<CharArray>, endPosition: Position): List<State> = listOf(
        0 to 1,
        0 to -1,
        1 to 0,
        -1 to 0,
    ).mapNotNull { (dy, dx) ->
        fun isRedMovable(newRed: Position, red: Position, blue: Position) =
            red != endPosition && newRed != blue

        fun isBlueMovable(newBlue: Position, red: Position) =
            // red 가 빠져나가도 blue 는 움직여야 한다.
            (red == endPosition || newBlue != red)

        var red = redPosition
        var blue = bluePosition

        var shouldContinue = true
        while (shouldContinue) {
            shouldContinue = false

            val newRed = red.getNextOrNull(dy, dx, map)
            if (newRed != null && isRedMovable(newRed, red, blue)) {
                red = newRed
                shouldContinue = true
            }

            val newBlue = blue.getNextOrNull(dy, dx, map)
            if (newBlue != null && isBlueMovable(newBlue, red)) {
                blue = newBlue
                shouldContinue = true
            }

            if (blue == endPosition)
                return@mapNotNull null
        }

        State(red, blue)
    }
}

fun bfs(
    map: Array<CharArray>,
    initialState: State,
    endPosition: Position,
): Int {
    val visitedAt = mutableMapOf<State, Int>()

    var level = 0
    var queue: Queue<State> = LinkedList<State>().apply { add(initialState) }

    while (queue.isNotEmpty() && level <= 10) {
        val toVisit = LinkedList<State>()

        queue.forEach { state ->
            if (state in visitedAt)
                return@forEach
            visitedAt[state] = level

            if (state.redPosition == endPosition) {
                return level
            }

            val nextStates = state.listNextStates(map, endPosition)
            toVisit.addAll(nextStates)
        }

        ++level
        queue = toVisit
    }

    return -1
}

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }

    val map = (0 until n)
        .map { readln().toCharArray() }
        .toTypedArray()

    var redPosition = Position(-1, -1)
    var bluePosition = Position(-1, -1)
    var endPosition = Position(-1, -1)
    map.forEachIndexed { y, row ->
        val foundRed = row.indexOfFirst { it == 'R' }
        if (foundRed != -1)
            redPosition = Position(y, foundRed)

        val foundBlue = row.indexOfFirst { it == 'B' }
        if (foundBlue != -1)
            bluePosition = Position(y, foundBlue)

        val foundEnd = row.indexOfFirst { it == 'O' }
        if (foundEnd != -1)
            endPosition = Position(y, foundEnd)
    }

    val initialState = State(redPosition, bluePosition)

    val result = bfs(map, initialState, endPosition)
    println(result)
}
