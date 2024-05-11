fun main() {
    val n = readln().toInt()
    val k = readln().toInt()

    val result = solve(n, k)

    println(result)
}

// `binarySearchBy`
fun innerExactParametricSearch(n: Int, i: Long, target: Long): Int {
    // indices
    var low = 0
    var high = n - 1

    while (low <= high) {
        // floor
        val mid = (low + high).ushr(1)
        val value = (mid + 1) * i

        if (value < target)
            low = mid + 1
        else if (value > target)
            high = mid - 1
        else
            return mid // key found
    }
    return -(low + 1) // key not found
}

// https://en.wikipedia.org/wiki/Binary_search_algorithm
fun leftmostParametricSearch(n: Int, target: Int): Long {
    var left = 1L
    var right = n.toLong() * n

    while (left < right) {
        // floor
        val mid = (left + right) / 2

        val value = (1L..n).sumOf { i ->
            val indexOrInsertionPoint = innerExactParametricSearch(n = n, i = i, target = mid)

            val leftElements = if (indexOrInsertionPoint < 0)
                -indexOrInsertionPoint - 1
            else
                indexOrInsertionPoint + 1

            leftElements.toLong()
        }

        if (value < target)
            left = mid + 1
        else
            right = mid
    }

    return left
}

fun solve(n: Int, k: Int): Long {
    return leftmostParametricSearch(n, k)
}
