fun solve(`as`: IntArray, bs: IntArray): Int {
    return `as`
        .sortedDescending()
        .zip(bs.sorted())
        .sumOf { (a, b) -> a * b }
}

fun main() {
    val n = readln().toInt()
    val `as` = readln().split(" ").map { it.toInt() }.toIntArray()
    val bs = readln().split(" ").map { it.toInt() }.toIntArray()

    val result = solve(`as`, bs)

    println(result)
}