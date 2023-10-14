class DisjointSet(n: Int) {
    private val parent = IntArray(n) { it }
    private val rank = IntArray(n) { 0 }

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
}

// O(e log (e + v))
// recommended for sparse graph
// requires undirected graph
// accepts unconnected graph, cyclic graph
fun kruskal(n: Int, edges: List<Triple<Int, Int, Int>>): Int {
    val disjointSet = DisjointSet(n)
    val sortedEdges = edges.sortedBy { it.third }
    var result = 0
    for ((a, b, weight) in sortedEdges) {
        if (disjointSet.find(a) != disjointSet.find(b)) {
            disjointSet.union(a, b)
            result += weight
        }
    }
    return result
}

fun main() {
    val n = readln().toInt()
    val m = readln().toInt()

    val edges = List(m) {
        readln()
            .split(" ")
            .map { it.toInt() }
            .let { (a, b, weight) -> Triple(a, b, weight) }
    }

    val result = kruskal(n + 1, edges)
    println(result)
}
