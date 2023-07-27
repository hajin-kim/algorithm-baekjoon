import java.util.*

fun splitWater(waterCapacities: IntArray, waterState: IntArray, from: Int, target: Int): IntArray {
    val targetCanReceive = waterCapacities[target] - waterState[target]
    val fromCanGive = waterState[from]
    val actualAmount = minOf(targetCanReceive, fromCanGive)
    return waterState.copyOf().apply {
        this[target] += actualAmount
        this[from] -= actualAmount
    }
}

fun getNeighborhoods(waterCapacities: IntArray, waterState: IntArray): List<IntArray> {
    val result = mutableListOf<IntArray>()
    for (i in waterCapacities.indices) {
        for (j in waterCapacities.indices) {
            if (i == j) continue
            val next = splitWater(waterCapacities, waterState, i, j)
            result.add(next)
        }
    }
    return result
}

fun IntArray.toKeyForVisited(): String {
    return this.joinToString(separator = "")
}

fun dfs(waterCapacities: IntArray): List<Int> {
    val start = IntArray(waterCapacities.size).apply { this[2] = waterCapacities[2] }

    val visited: HashMap<String, Boolean> = HashMap()
    val toVisit: Stack<IntArray> = Stack<IntArray>().apply { push(start) }
    val result = TreeSet<Int>()

    while (toVisit.isNotEmpty()) {
        val waterState = toVisit.pop()
        val key = waterState.toKeyForVisited()
        if (visited[key] == null) {
            visited[key] = true
            if (waterState[0] == 0)
                result.add(waterState[2])
            val neighbors = getNeighborhoods(waterCapacities, waterState).reversed()
            toVisit.addAll(neighbors)
        }
    }

    return result.toList()
}

fun main(args: Array<String>) {
    val waterCapacities = readln().split(" ").map { it.toInt() }.toIntArray()

    val dfsResult = dfs(waterCapacities)

    println(dfsResult.joinToString(" "))
}
