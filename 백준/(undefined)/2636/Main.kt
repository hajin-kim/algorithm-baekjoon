import java.util.*

data class Point(val x: Int, val y: Int)

fun Char.toBias() = if (this == '1') 1 else 0

fun bfs(nRows: Int, nColumns: Int, field: Array<Array<Char>>): Pair<Int, Int> {
    val visited = Array(nRows) { Array(nColumns) { 0 } }
    var toVisit: Queue<Pair<Point, Int>> = LinkedList<Pair<Point, Int>>().apply { add(Point(0, 0) to 1) }

    while (toVisit.isNotEmpty()) {
        val toVisitOnNext: Queue<Pair<Point, Int>> = LinkedList()

        toVisit.forEach { (point, timeElapsed) ->
            val (x, y) = point
            val visitedTimeElapsed = visited[y][x]

            val newTimeElapsed = timeElapsed + field[y][x].toBias()
            if (visitedTimeElapsed == 0 || visitedTimeElapsed > newTimeElapsed) {
                visited[y][x] = newTimeElapsed
                val neighbors = mutableListOf<Pair<Point, Int>>()
                if (x > 0)
                    neighbors.add(Point(x - 1, y) to newTimeElapsed)
                if (x < nColumns - 1)
                    neighbors.add(Point(x + 1, y) to newTimeElapsed)
                if (y > 0)
                    neighbors.add(Point(x, y - 1) to newTimeElapsed)
                if (y < nRows - 1)
                    neighbors.add(Point(x, y + 1) to newTimeElapsed)
                toVisitOnNext.addAll(neighbors)
            }
        }

        toVisit = toVisitOnNext
    }

    val result = visited.maxOf { it.max() }
    val remainingCheese = visited
        .zip(field)
        .map { (xs, ys) ->
            xs.zip(ys).toTypedArray()
        }
        .toTypedArray()
        .sumOf { xs -> xs.count { it.first == result && it.second == '1' } }
    val finalTimeElapsed = result - 1

    return finalTimeElapsed to remainingCheese
}

fun main(args: Array<String>) {
    val (nRows, nColumns) = readln().split(" ").map { it.toInt() }

    val field = (1..nRows)
        .map {
            readln().split(" ").map { it.first() }.toTypedArray()
        }
        .toTypedArray()

    val (finalTimeElapsed, remainingCheese) = bfs(nRows, nColumns, field)

    println(finalTimeElapsed)
    println(remainingCheese)
}