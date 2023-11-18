import java.math.BigDecimal

const val MOD = 10007
fun solve(n: Int): Int {
    var result = BigDecimal(1)
    var multiplier = BigDecimal(10)
    var divisor = BigDecimal(1)
    repeat(n) {
        result = result * multiplier / divisor
        ++multiplier
        ++divisor
    }

    return result.remainder(MOD.toBigDecimal()).intValueExact()
}

fun main() {
    val n = readln().toInt()

    val result = solve(n)

    println(result)
}