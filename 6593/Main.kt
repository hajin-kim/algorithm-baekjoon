import java.util.*

data class Point(val x: Int, val y: Int, val z: Int)

fun bfs(building: Array<Array<CharArray>>): Int? {
    val n = building.size
    val r = building[0].size
    val c = building[0][0].size

    lateinit var start: Point
    for (z in 0 until n) {
        for (y in 0 until r) {
            for (x in 0 until c) {
                if (building[z][y][x] == 'S')
                    start = Point(x, y, z)
            }
        }
    }

    val visited = Array(n) { Array(r) { BooleanArray(c) } }
    var toVisit: Queue<Point> = LinkedList<Point>().apply { add(start) }

    var score = 0
    while (toVisit.isNotEmpty()) {
        val nextToVisit = LinkedList<Point>()

        toVisit.forEach { node ->
            val (x, y, z) = node
            if (visited[z][y][x])
                return@forEach
            visited[z][y][x] = true

            val value = building[z][y][x]
            if (value == '#')
                return@forEach
            if (value == 'E')
                return score

            val nextPoints = mutableListOf<Point>()
            if (x > 0) nextPoints.add(Point(x - 1, y, z))
            if (y > 0) nextPoints.add(Point(x, y - 1, z))
            if (z > 0) nextPoints.add(Point(x, y, z - 1))
            if (x < c - 1) nextPoints.add(Point(x + 1, y, z))
            if (y < r - 1) nextPoints.add(Point(x, y + 1, z))
            if (z < n - 1) nextPoints.add(Point(x, y, z + 1))
            nextToVisit.addAll(nextPoints)
        }

        toVisit = nextToVisit
        ++score
    }

    return null
}

fun main(args: Array<String>) {
    while (true) {
        val (n, r, c) = readln().split(" ").map { it.toInt() }
        if (n == 0 && r == 0 && c == 0)
            break

        val building = (1..n)
            .map {
                val ret = (1..r).map { readln().toCharArray() }.toTypedArray()
                readln()
                ret
            }
            .toTypedArray()

        val score = bfs(building)
        if (score != null) {
            println("Escaped in $score minute(s).")
        } else {
            println("Trapped!")
            continue
        }
    }
}