import java.math.BigDecimal

fun solve(a: BigDecimal, b: BigDecimal): Triple<BigDecimal, BigDecimal, BigDecimal> {
    return Triple(
        a + b,
        a - b,
        a * b,
    )
}

fun main() {
    val a = readln().toBigDecimal()
    val b = readln().toBigDecimal()

    val result = solve(a, b)

    println(result.toList().joinToString("\n"))
}