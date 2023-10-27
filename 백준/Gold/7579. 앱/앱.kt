const val DEFAULT = -1
fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val memories = readln().split(" ").map { it.toInt() }.toIntArray()
    val costs = readln().split(" ").map { it.toInt() }.toIntArray()

    val maxCost = 100 * n
    var memoryByCost = IntArray(maxCost + 1) { DEFAULT }
    memoryByCost[0] = 0

    for (i in 0 until n) {
        val new = memoryByCost.copyOf()

        val memory = memories[i]
        val cost = costs[i]

        for (costIter in 0..maxCost - cost) {
            val newCost = costIter + cost
            if (memoryByCost[costIter] != DEFAULT) {
                new[newCost] = maxOf(memoryByCost[newCost], memoryByCost[costIter] + memory)
            }
        }

        memoryByCost = new
    }

    val result = memoryByCost.indexOfFirst { it >= m }

    println(result)
}