import kotlin.math.hypot

fun Pair<Int, Int>.isInCircle(circle: Triple<Int, Int, Int>): Boolean {
    val (x, y) = this
    val (cx, cy, r) = circle

    return hypot((x - cx).toDouble(), (y - cy).toDouble()) <= r
}

fun solve(start: Pair<Int, Int>, end: Pair<Int, Int>, circles: Array<Triple<Int, Int, Int>>): Int {
    return circles.count { circle ->
        val startIsIn = start.isInCircle(circle)
        val endIsIn = end.isInCircle(circle)

        startIsIn xor endIsIn
    }
}

fun main() {
    val t = readln().toInt()
    repeat(t) {
        val (start, end) = readln().split(" ").map { it.toInt() }
            .let { (it[0] to it[1]) to (it[2] to it[3]) }
        val n = readln().toInt()
        val circles = Array(n) {
            readln().split(" ").map { it.toInt() }
                .let { Triple(it[0], it[1], it[2]) }
        }

        val result = solve(start, end, circles)

        println(result)
    }
}