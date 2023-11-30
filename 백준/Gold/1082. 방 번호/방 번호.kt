import java.math.BigDecimal

val UNDEFINED = BigDecimal(-1)

fun solve(n: Int, prices: IntArray, m: Int): BigDecimal {
    val cache = Array(m + 1) { UNDEFINED }

    for (number in prices.indices) {
        val price = prices[number]

        if (price > m)
            continue

        cache[price] = number.toBigDecimal()
    }

    for (price in 0 until m) {
        if (cache[price] == UNDEFINED)
            continue

        for (number in prices.indices) {
            val nextPrice = price + prices[number]

            if (nextPrice > m)
                continue

            val nowStringValue = cache[price].toString()
            cache[nextPrice] = maxOf(
                cache[nextPrice],
                BigDecimal(nowStringValue + number),
                BigDecimal(number.toString() + nowStringValue),
            )
        }
    }

    return cache.max()
}

fun main() {
    val n = readln().toInt()
    val ps = readln().split(" ").map { it.toInt() }.toIntArray()
    val m = readln().toInt()

    val result = solve(n, ps, m)

    println(result)
}