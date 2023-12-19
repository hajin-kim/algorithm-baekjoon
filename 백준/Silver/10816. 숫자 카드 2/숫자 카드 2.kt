fun solve(numbers: IntArray, queries: IntArray): List<Int> {
    val numberCount = numbers
        .groupBy { it }
        .mapValues { (_, v) -> v.size }

    return queries.map { numberCount[it] ?: 0 }
}

fun main() {
    val n = readln().toInt()
    val numbers = readln().split(" ").map { it.toInt() }.toIntArray()
    val m = readln().toInt()
    val queries = readln().split(" ").map { it.toInt() }.toIntArray()

    val result = solve(numbers, queries)

    println(result.joinToString(" "))
}