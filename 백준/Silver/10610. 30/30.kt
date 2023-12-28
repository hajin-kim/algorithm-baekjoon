import java.math.BigDecimal

fun solve(n: String): String {

    val max = n.toList()
        .sortedDescending()
        .joinToString("")

    if (max.toBigDecimal().rem(BigDecimal(30)) != BigDecimal.ZERO)
        return "-1"

    return max
}

fun main() {
    val n = readln()

    val result = solve(n)

    println(result)
}