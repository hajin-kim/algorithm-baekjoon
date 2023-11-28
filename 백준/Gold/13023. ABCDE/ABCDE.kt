fun dfs(start: Int, n: Int, edgeArray: Array<out List<Int>>): Boolean {
    val visited = BooleanArray(n)

    fun dfsInternal(now: Int, depth: Int): Boolean {
        if (visited[now])
            return false
        visited[now] = true

        if (depth == 4)
            return true

        if (edgeArray[now].any { dfsInternal(it, depth + 1) })
            return true

        visited[now] = false
        return false
    }

    return dfsInternal(start, 0)
}

fun solve(n: Int, edges: List<Pair<Int, Int>>): Boolean {
    val edgeArray = Array(n) { mutableListOf<Int>() }
    for ((a, b) in edges) {
        edgeArray[a].add(b)
        edgeArray[b].add(a)
    }

    return (0 until n).any { dfs(it, n, edgeArray) }
}

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val edges = List(m) { readln().split(" ").map { it.toInt() }.let { it[0] to it[1] } }

    val result = solve(n, edges)

    println(if (result) 1 else 0)
}