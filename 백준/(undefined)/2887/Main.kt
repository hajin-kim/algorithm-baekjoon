fun kruskal(n: Int, edges: List<Triple<Int, Int, Int>>): Int {
    val parent = IntArray(n) { it }
    val rank = IntArray(n) { 0 }

    fun find(node: Int): Int {
        if (parent[node] == node) return node
        parent[node] = find(parent[node])
        return parent[node]
    }

    fun union(a: Int, b: Int) {
        val aRoot = find(a)
        val bRoot = find(b)

        if (aRoot == bRoot) return

        if (rank[aRoot] < rank[bRoot]) {
            parent[aRoot] = bRoot
        } else if (rank[aRoot] > rank[bRoot]) {
            parent[bRoot] = aRoot
        } else {
            parent[bRoot] = aRoot
            rank[aRoot]++
        }
    }

    val sortedEdges = edges.sortedBy { it.third }
    var result = 0
    for ((a, b, weight) in sortedEdges) {
        if (find(a) != find(b)) {
            union(a, b)
            result += weight
        }
    }
    return result
}

fun main() {
    val n = readln().toInt()

    // x, y, z
    val planets = (1..n)
        .map {
            readln().split(" ").map { it.toInt() }.let { (x, y, z) -> Triple(x, y, z) }
        }
        .withIndex()

    // a, b, weight
    val edges = mutableListOf<Triple<Int, Int, Int>>()
    planets.sortedBy { it.value.first }.zipWithNext().forEach { (a, b) ->
        edges.add(Triple(a.index, b.index, b.value.first - a.value.first))
    }
    planets.sortedBy { it.value.second }.zipWithNext().forEach { (a, b) ->
        edges.add(Triple(a.index, b.index, b.value.second - a.value.second))
    }
    planets.sortedBy { it.value.third }.zipWithNext().forEach { (a, b) ->
        edges.add(Triple(a.index, b.index, b.value.third - a.value.third))
    }

    val result = kruskal(n, edges)
    println(result)
}
