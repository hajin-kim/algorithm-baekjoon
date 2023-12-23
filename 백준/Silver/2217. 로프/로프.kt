fun solve(n: Int, weights: Array<Int>): Int {
    return weights
        .sortedDescending()
        .withIndex()
        .maxOf { (index, value) -> (index + 1) * value }
}

fun main() {
    val n = readln().toInt()
    val weights = Array(n) {
        readln().toInt()
    }

    val result = solve(n, weights)

    println(result)
}