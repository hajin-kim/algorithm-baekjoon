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

fun solve(n: Int, edgeArray: Array<BooleanArray>, plan: List<Int>): Boolean {
    val disjointSet = DisjointSet(n)

    for (city in 0 until n) {
        for (neighbor in 0 until n) {
            if (edgeArray[city][neighbor])
                disjointSet.union(city, neighbor)
        }
    }

    return plan
        .map { it - 1 }
        .zipWithNext()
        .all { (a, b) -> disjointSet.find(a) == disjointSet.find(b) }
}

fun main() {
    val n = readln().toInt()
    val m = readln().toInt()

    val edgeArray = Array(n) {
        readln().split(" ").map { it == "1" }.toBooleanArray()
    }

    val plan = readln().split(" ").map { it.toInt() }

    val result = solve(n, edgeArray, plan)

    println(if (result) "YES" else "NO")
}