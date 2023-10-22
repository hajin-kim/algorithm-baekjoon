import kotlin.math.abs

fun solve(n: Int, liquids: List<Int>): Pair<Int, Int> {
    val sorted = liquids.sorted().toIntArray()

    var left = 0
    var right = n - 1

    var leftLiquid = sorted[left]
    var rightLiquid = sorted[right]

    while (right - left > 1) {
        if (sorted[right] + sorted[left] > 0) {
            right--
        } else {
            left++
        }

        if (abs(sorted[right] + sorted[left]) < abs(rightLiquid + leftLiquid)) {
            leftLiquid = sorted[left]
            rightLiquid = sorted[right]
        }
    }

    return leftLiquid to rightLiquid
}

fun main() {
    val n = readln().toInt()
    val liquids = readln().split(" ").map { it.toInt() }

    val result = solve(n, liquids)

    println("${result.first} ${result.second}")
}