import kotlin.math.pow

fun solve(n: Int): Long {
    @Suppress("NAME_SHADOWING") var n = n
    var result = 0L
    var count = 0
    while (n >= 1) {
        val increment = (9 * 10.0.pow(count).toInt()).coerceAtMost(n)
        result += increment * (count + 1)
        n -= increment
        count++
    }
    return result
}

fun main() {
    val n = readln().toInt()

    val result = solve(n)

    println(result)
}