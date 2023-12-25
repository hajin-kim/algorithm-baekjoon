fun solve(ps: IntArray): Int {
    return ps
        .sortedDescending()
        .withIndex()
        .sumOf { (index, value) -> (index + 1) * value }
}

fun main() {
    val n = readln().toInt()
    val ps = readln().split(" ").map { it.toInt() }.toIntArray()

    val result = solve(ps)

    println(result)
}