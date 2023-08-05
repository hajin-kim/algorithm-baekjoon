import java.util.*
import kotlin.math.max
import kotlin.math.min

data class Point(val x: Int, val y: Int)

data class ReducedRange(var start: Int, var end: Int)

fun main(args: Array<String>) {
    val n = readln().toInt()
    val points = (1..n).map {
        readln()
            .split(" ")
            .map { it.toInt() }
            .let { Point(it[0], it[1]) }
    }

    // assert first crossing is upward
    var firstIndexOfUp = n - 1
    for (i in 0 until n - 1) {
        if (points[i].y < 0 && points[i + 1].y > 0) {
            firstIndexOfUp = i
            break
        }
    }
    val rotatedPoints = points.drop(firstIndexOfUp) + points.take(firstIndexOfUp)

    val crossingXs = mutableListOf<Int>()
    rotatedPoints.reduce { previous, current ->
        if (previous.y < 0 && current.y > 0)
            crossingXs.add(current.x)
        else if (previous.y > 0 && current.y < 0)
            crossingXs.add(current.x)

        current
    }

    val ranges = crossingXs
        .chunked(2)
        .map { (first, second) ->
            val start = min(first, second)
            val end = max(first, second)
            ReducedRange(start, end)
        }
        .sortedBy { it.start }
        .plus(ReducedRange(Int.MAX_VALUE, Int.MAX_VALUE))

    val stack = Stack<ReducedRange>()
    var nDiagramsUnenclosed = 0
    var nDiagramsNotEnclosing = 0

    ranges.forEach {
        var isFirstPop = true
        while (stack.isNotEmpty() && stack.peek().end < it.start) {
            if (isFirstPop) {
                ++nDiagramsNotEnclosing
                isFirstPop = false
            }
            stack.pop()
            if (stack.isEmpty())
                ++nDiagramsUnenclosed
        }
        stack.push(it)
    }

    println("$nDiagramsUnenclosed $nDiagramsNotEnclosing")
}