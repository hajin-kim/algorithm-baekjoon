fun treeDfs(start: Int, manageesByManager: Map<Int, List<Int>>): Int {
    val children = manageesByManager[start] ?: return 0

    return children
        .map { treeDfs(it, manageesByManager) }
        .sortedDescending()
        .withIndex()
        .maxOf { (index, value) -> value + index + 1 }
}

fun main(args: Array<String>) {
    val n = readln().toInt()
    val manageesByManager = readln()
        .split(" ")
        .withIndex()
        .groupBy({ it.value.toInt() }, { it.index })

    val result = treeDfs(0, manageesByManager)

    println(result)
}