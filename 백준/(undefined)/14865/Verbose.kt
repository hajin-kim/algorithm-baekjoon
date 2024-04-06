import java.util.*
import kotlin.math.max
import kotlin.math.min

enum class State {
    UP, DOWN, MAX
}

data class Point(val x: Int, val y: Int)

data class ReducedDiagram(var start: Int, var end: Int, var max: Int)

fun main(args: Array<String>) {
    val n = readln().toInt()
    val points = (1..n).map {
        readln()
            .split(" ")
            .map { it.toInt() }
            .let { Point(it[0], it[1]) }
    }

    var firstIndexOfUp = n - 1
    for (i in 0 until n - 1) {
        if (points[i].y < 0 && points[i + 1].y > 0) {
            firstIndexOfUp = i
            break
        }
    }
    val rotatedPoints = points.drop(firstIndexOfUp) + points.take(firstIndexOfUp)

    val reducedDiagrams = mutableListOf<ReducedDiagram>()
    rotatedPoints.plus(rotatedPoints.first()).reduce { previous, current ->
        val state = when {
            // no case that y == 0
            previous.y < 0 && current.y > 0 -> State.UP
            previous.y > 0 && current.y < 0 -> State.DOWN
            previous.y > 0 && current.y > previous.y -> State.MAX
            else -> null
        }

        when (state) {
            State.UP -> reducedDiagrams.add(ReducedDiagram(current.x, 0, current.y))
            State.DOWN -> reducedDiagrams.last().apply { end = current.x }
            State.MAX -> reducedDiagrams.last().apply { max = current.y }
            null -> {}
        }

        current
    }
    reducedDiagrams.forEach {
        val start = min(it.start, it.end)
        val end = max(it.start, it.end)
        it.start = start
        it.end = end
    }
    reducedDiagrams.sortBy { it.start }
    reducedDiagrams.add(ReducedDiagram(Int.MAX_VALUE, Int.MAX_VALUE, Int.MAX_VALUE))

    val stack = Stack<ReducedDiagram>()
    var nDiagramsUnenclosed = 0
    var nDiagramsNotEnclosing = 0

    reducedDiagrams.forEach {
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