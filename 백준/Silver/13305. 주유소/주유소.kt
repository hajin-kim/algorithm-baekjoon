fun main() {
    val n = readln().toInt()
    val edges = readln().split(" ").map { it.toInt() }
    val prices = readln().split(" ").map { it.toInt() }.toIntArray()

    val total = edges.sum()
    var remaining = total

    val result = edges
        .mapIndexed { index, edge ->
            val prop = prices[index] to remaining
            remaining -= edge

            prop
        }
        .sortedBy { it.first }
        .sumOf { (price, distance) ->
            val assigned = (distance - remaining).coerceAtLeast(0)

            remaining += assigned

            price.toLong() * assigned
        }

    println(result)
}