import java.util.*

data class Point(val x: Int, val y: Int) {
    fun toTriple(map: Array<IntArray>, records: Array<IntArray>) = Triple(getHeight(map), this, getRecord(records))

    fun getHeight(map: Array<IntArray>) = map[y][x]

    private fun getRecord(records: Array<IntArray>) = records[y][x]
}

fun pfs(map: Array<IntArray>, depth: Int, width: Int): Int {
    val records = Array(depth) { IntArray(width) }.apply { this[0][0] = 1 }
    val visited = Array(depth) { BooleanArray(width) }

    val start = Point(0, 0)
    val toVisit = PriorityQueue(
        depth * width,
        compareByDescending<Triple<Int, Point, Int>> { it.first }.thenByDescending { it.third })
        .apply { add(start.toTriple(map, records)) }

    while (toVisit.isNotEmpty()) {
        val (height, point, nWays) = toVisit.poll()
        val (x, y) = point

        if (!visited[y][x] && (y < depth - 1 || x < width - 1)) {
            val newPoints = mutableListOf<Point>()
            if (x > 0)
                newPoints.add(Point(x - 1, y))
            if (x < width - 1)
                newPoints.add(Point(x + 1, y))
            if (y > 0)
                newPoints.add(Point(x, y - 1))
            if (y < depth - 1)
                newPoints.add(Point(x, y + 1))

            newPoints
                .filter { it.getHeight(map) < height }
                .forEach {
                    val (newX, newY) = it
                    records[newY][newX] += nWays
                    toVisit.add(it.toTriple(map, records))
                }
        }
        visited[y][x] = true
    }

    return records[depth - 1][width - 1]
}

fun main(args: Array<String>) {
    val (depth, width) = readln().split(" ").map { it.toInt() }

    val map = (1..depth)
        .map {
            readln().split(" ").map { it.toInt() }.toIntArray()
        }
        .toTypedArray()

    pfs(map, depth, width).let(::println)
}