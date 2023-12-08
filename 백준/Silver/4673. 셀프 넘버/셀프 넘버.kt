const val LIMIT = 10_000

fun Int.d(): Int =
    this.toString().fold(this) { acc, c -> acc + c.digitToInt() }

fun solve(): List<Int> {
    val isSelfNumber = BooleanArray(LIMIT + 1) { true }
    isSelfNumber[0] = false

    for (number in 1..LIMIT) {
        val d = number.d()
        if (d <= LIMIT)
            isSelfNumber[d] = false
    }

    return isSelfNumber
        .withIndex()
        .filter { it.value }
        .map { it.index }
}

fun main() {
    val result = solve()

    println(result.joinToString("\n"))
}