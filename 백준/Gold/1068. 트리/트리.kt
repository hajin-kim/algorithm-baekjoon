fun traverse(now: Int, edges: Map<Int, List<Int>>): Int {
    edges[now] ?: return 1

    return edges[now]!!
        .sumOf { traverse(it, edges) }
}

fun solve(parents: IntArray, toDelete: Int): Int {
    val edges = parents
        .withIndex()
        .filter { it.index != toDelete }
        .groupBy({ it.value }, { it.index })

    edges[-1] ?: return 0

    val root = edges[-1]!![0]

    return traverse(root, edges)
}

fun main() {
    val n = readln().toInt()
    val parents = readln().split(" ").map { it.toInt() }.toIntArray()
    val toDelete = readln().toInt()

    val result = solve(parents, toDelete)

    println(result)
}