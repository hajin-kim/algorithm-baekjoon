fun main() {
    val n = readln().toInt()
    val edges = (0 until n - 1)
        .map { readln().split(" ").let { it[0].toInt() to it[1].toInt() } }
        .flatMap { listOf(it, it.second to it.first) }
        .groupBy({ it.first }, { it.second })

    val m = readln().toInt()
    val queries = (1..m).map { readln().split(" ").let { it[0].toInt() to it[1].toInt() } }

    val parents = IntArray(n + 1) { it }
    val depths = IntArray(n + 1) { 0 }
    val visited = BooleanArray(n + 1)
    fun traverse(parent: Int, node: Int, depth: Int) {
        if (visited[node])
            return
        visited[node] = true

        depths[node] = depth
        parents[node] = parent

        edges[node]?.forEach { child ->
            traverse(node, child, depth + 1)
        }
    }
    traverse(1, 1, 0)

    val cache = mutableMapOf<Pair<Int, Int>, Int>()
    fun lca(a: Int, b: Int): Int {
        val key = minOf(a, b) to maxOf(a, b)
        if (key in cache)
            return cache[key]!!

        if (a == b)
            return a

        val result = if (depths[a] > depths[b]) {
            lca(parents[a], b)
        } else if (depths[a] < depths[b]) {
            lca(a, parents[b])
        } else {
            lca(parents[a], parents[b])
        }

        cache[key] = result
        return result
    }

    queries.forEach { (a, b) ->
        println(lca(a, b))
    }
}