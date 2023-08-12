import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {
    val orders = readln().split(" ").dropLast(1).map { it.toInt() }

    fun calcluateUsage(from: Int, to: Int): Int {
        return when (from) {
            to -> 1
            0 -> 2
            1 -> if (to == 3) 4 else 3
            2 -> if (to == 4) 4 else 3
            3 -> if (to == 1) 4 else 3
            4 -> if (to == 2) 4 else 3
            else -> -1
        }
    }

    fun toPosition(a: Int, b: Int) = min(a, b) to max(a, b)

    val initialPosition = toPosition(0, orders.first())
    var positionToUsage = mutableMapOf(initialPosition to 2)
    orders.drop(1).forEach {
        val newPositionToScore = mutableMapOf<Pair<Int, Int>, Int>()

        val candidates = positionToUsage.flatMap { (position, usage) ->
            val (a, b) = position

            val moveAUsage = usage + calcluateUsage(a, it)
            val moveAPosition = toPosition(it, b)

            val moveBUsage = usage + calcluateUsage(b, it)
            val moveBPosition = toPosition(a, it)

            listOf(
                moveAPosition to moveAUsage,
                moveBPosition to moveBUsage,
            )
        }

        candidates.forEach { (position, usage) ->
            val existing = newPositionToScore[position]
            if (existing == null || existing > usage) {
                newPositionToScore[position] = usage
            }
        }

        positionToUsage = newPositionToScore
    }

    println(positionToUsage.values.min())
}
