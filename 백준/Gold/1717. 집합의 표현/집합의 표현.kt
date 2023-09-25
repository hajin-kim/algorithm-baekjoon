class DisjointSet(val n: Int) {
    private val parent = IntArray(n) { it }
    private val rank = IntArray(n) { 0 }

    fun find(x: Int): Int {
        if (parent[x] == x)
            return x
        parent[x] = find(parent[x])
        return parent[x]
    }

    fun union(x: Int, y: Int) {
        val xRoot = find(x)
        val yRoot = find(y)

        if (xRoot == yRoot)
            return

        if (rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot
        } else if (rank[xRoot] > rank[yRoot]) {
            parent[yRoot] = xRoot
        } else {
            parent[yRoot] = xRoot
            rank[xRoot]++
        }
    }
}

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }

    val disjointSet = DisjointSet(n + 1)
    val result = mutableListOf<Boolean>()
    repeat(m) {
        val (command, a, b) = readln().split(" ").map { it.toInt() }
        when (command) {
            0 -> disjointSet.union(a, b)
            1 -> result.add(disjointSet.find(a) == disjointSet.find(b))
        }
    }

    println(result.joinToString("\n") { if (it) "YES" else "NO" })
}