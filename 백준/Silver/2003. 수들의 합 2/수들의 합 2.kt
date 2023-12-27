fun solve(m: Int, sequence: IntArray): Int {
    var result = 0
    var sum = 0
    var left = 0

    for (right in sequence.indices) {
        sum += sequence[right]

        while (sum >= m) {
            if (sum == m)
                ++result
            sum -= sequence[left]
            ++left
        }
    }

    return result
}

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val sequence = readln().split(" ").map { it.toInt() }.toIntArray()

    val result = solve(m, sequence)

    println(result)
}