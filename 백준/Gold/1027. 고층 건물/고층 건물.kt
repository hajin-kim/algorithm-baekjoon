fun Int.getGradient(heights: IntArray, next: Int): Double {
    val distance = next - this
    return (heights[next] - heights[this]).toDouble() / distance
}

fun solve(n: Int, heights: IntArray): Int {

    var result = 0
    repeat(n) {
        var visibleBuildings = 0

        var gradient = Double.NEGATIVE_INFINITY
        for (right in it + 1 until n) {
            val newGradient = it.getGradient(heights, right)
            if (newGradient > gradient) {
                visibleBuildings++
                gradient = newGradient
            }
        }

        gradient = Double.POSITIVE_INFINITY
        for (left in it - 1 downTo 0) {
            val newGradient = left.getGradient(heights, it)
            if (newGradient < gradient) {
                visibleBuildings++
                gradient = newGradient
            }
        }

        result = maxOf(result, visibleBuildings)
    }

    return result
}

fun main() {
    val n = readln().toInt()
    val heights = readln().split(" ").map { it.toInt() }.toIntArray()

    val result = solve(n, heights)

    println(result)
}