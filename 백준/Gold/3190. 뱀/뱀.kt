import java.util.*

val directions = arrayOf(
    0 to 1,
    1 to 0,
    0 to -1,
    -1 to 0,
)

fun changeDirection(current: Int, c: Char): Int {
    return if (c == 'D')
        (current + 1) % 4
    else
        (current - 1 + 4) % 4
}

fun getNextPosition(current: Pair<Int, Int>, direction: Int): Pair<Int, Int> {
    val (y, x) = current
    val (dy, dx) = directions[direction]
    return y + dy to x + dx
}

fun isInRange(n: Int, y: Int, x: Int): Boolean {
    return y in 1..n && x in 1..n
}

fun main() {
    val n = readln().toInt()
    val k = readln().toInt()

    val apples = List(k) {
        readln().split(" ").map { it.toInt() }
            .let { it[0] to it[1] }
    }

    val l = readln().toInt()

    val directionDeltas = List(l) {
        readln().split(" ")
            .let { it[0].toInt() to it[1][0] }
    }
    val directionQueue: Queue<Pair<Int, Char>> = LinkedList(directionDeltas)


    val isApple = Array(n + 1) { BooleanArray(n + 1) }
    apples.forEach { (y, x) ->
        isApple[y][x] = true
    }

    val snakeBodies: Queue<Pair<Int, Int>> = LinkedList()
    snakeBodies.add(1 to 1)
    val isSnake = Array(n + 1) { BooleanArray(n + 1) }
    isSnake[1][1] = true

    var direction = 0
    var time = 1
    var lastPosition = 1 to 1

    while (true) {
        val (y, x) = getNextPosition(lastPosition, direction)

        if (!isInRange(n, y, x) || isSnake[y][x])
            break

        isSnake[y][x] = true
        snakeBodies.add(y to x)

        if (isApple[y][x]) {
            isApple[y][x] = false
        } else {
            val (firstY, firstX) = snakeBodies.poll()
            isSnake[firstY][firstX] = false
        }

        if (directionQueue.isNotEmpty() && directionQueue.peek().first == time) {
            val (_, c) = directionQueue.poll()
            direction = changeDirection(direction, c)
        }

        lastPosition = y to x
        ++time
    }

    println(time)
}