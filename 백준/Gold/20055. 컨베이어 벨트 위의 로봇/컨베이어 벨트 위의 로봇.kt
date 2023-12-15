fun Int.decreaseWithin(n: Int) = ((this - 1) + n) % n
fun Int.increaseWithin(n: Int) = ((this + 1) + n) % n

fun solve(n: Int, k: Int, aArray: IntArray): Int {
    val isRobot = BooleanArray(2 * n)

    var step = 1

    var insertPos = 0
    var removePos = n - 1

    while (true) {
        // move conveyor belt (pointer)
        insertPos = insertPos.decreaseWithin(2 * n)
        removePos = removePos.decreaseWithin(2 * n)

        isRobot[removePos] = false

        val orderedRange = if (insertPos < removePos)
            removePos - 1 downTo insertPos
        else ((removePos - 1 downTo 0) + (2 * n - 1 downTo insertPos))

        // move robot
        for (i in orderedRange) {
            val next = i.increaseWithin(2 * n)
            if (isRobot[i] && !isRobot[next] && aArray[next] > 0) {
                isRobot[i] = false
                aArray[next]--
                isRobot[next] = true
            }
        }
        isRobot[removePos] = false

        if (aArray[insertPos] > 0) {
            isRobot[insertPos] = true
            aArray[insertPos]--
        }

        if (aArray.count { it == 0 } >= k)
            break

        ++step
    }

    return step
}

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val aArray =
        readln().split(" ").map { it.toInt() }.toIntArray()

    val result = solve(n, k, aArray)

    println(result)
}