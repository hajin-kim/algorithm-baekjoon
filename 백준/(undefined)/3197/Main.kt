import java.util.*

data class Position(val x: Int, val y: Int)

class DisjointSet(size: Int) {
    private val parent = IntArray(size) { it }
    private val rank = IntArray(size)

    fun find(a: Int): Int {
        if (parent[a] != a) {
            parent[a] = find(parent[a])
        }
        return parent[a]
    }

    fun union(a: Int, b: Int) {
        val aRoot = find(a)
        val bRoot = find(b)

        if (aRoot == bRoot)
            return

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

fun bfs(isWater: Array<BooleanArray>, start: Position, end: Position): Int {
    val r = isWater.size
    val c = isWater[0].size

    val visited = Array(r) { BooleanArray(c) }

    val disjointSet = DisjointSet(r * c)

    fun find(position: Position): Int =
        disjointSet.find(position.y * c + position.x)

    fun union(a: Position, b: Position) =
        disjointSet.union(a.y * c + a.x, b.y * c + b.x)

    fun Position.getNeighbors(): List<Position> {
        val neighbors = mutableListOf<Position>()
        val (x, y) = this
        if (x > 0)
            neighbors.add(Position(x - 1, y))
        if (x < c - 1)
            neighbors.add(Position(x + 1, y))
        if (y > 0)
            neighbors.add(Position(x, y - 1))
        if (y < r - 1)
            neighbors.add(Position(x, y + 1))
        return neighbors
    }

    var queue: Queue<Position> = LinkedList()
    isWater.forEachIndexed { y, row ->
        row.forEachIndexed { x, it -> if (it) queue.add(Position(x, y)) }
    }

    var time = 0
    while (queue.isNotEmpty()) {
        val toVisit = LinkedList<Position>()

        queue.forEach { current ->
            if (visited[current.y][current.x])
                return@forEach
            visited[current.y][current.x] = true
            val neighbors = current.getNeighbors()
            for (neighbor in neighbors) {
                if (!visited[neighbor.y][neighbor.x]) {
                    toVisit.add(neighbor)
                } else {
                    union(current, neighbor)
                }
            }
        }

        if (find(start) == find(end))
            return time
        ++time
        queue = toVisit
    }

    return -1
}

fun main(args: Array<String>) {
    val swans = mutableListOf<Position>()

    val (r, c) = readln().split(" ").map { it.toInt() }
    val isWater = Array(r) { y ->
        readln()
            .mapIndexed { x, it ->
                if (it == 'L')
                    swans.add(Position(x, y))
                it != 'X'
            }
            .toBooleanArray()
    }

    val result = bfs(isWater, swans[0], swans[1])
    println(result)
}