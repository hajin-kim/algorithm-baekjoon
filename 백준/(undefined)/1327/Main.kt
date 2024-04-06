import java.util.*

fun flip(numbers: IntArray, from: Int, k: Int): IntArray {
    assert(from + k <= numbers.size)
    return numbers
        .take(from)
        .plus(numbers.slice(from until from + k).reversed())
        .plus(numbers.drop(from + k))
        .toIntArray()
}

fun getNeighborhoods(numbers: IntArray, k: Int): List<IntArray> {
    return (0..numbers.size - k).map { flip(numbers, it, k) }
}

fun IntArray.isSorted(): Boolean {
    return this.sortedArray().contentEquals(this)
}

fun IntArray.toKeyForVisited(): String {
    return this.joinToString(separator = "")
}

fun bfs(start: IntArray, k: Int): Int {
    val visited: HashMap<String, Boolean> = HashMap()
    var toVisit: Queue<IntArray> = LinkedList<IntArray>().apply { add(start) }

    var count = 0
    while (toVisit.isNotEmpty()) {
        val toVisitOnNext: Queue<IntArray> = LinkedList()

        toVisit.forEach { numbers ->
            val key = numbers.toKeyForVisited()
            if (visited[key] == null) {
                visited[key] = true
                if (numbers.isSorted()) {
                    return count
                }
                val neighbors = getNeighborhoods(numbers, k)
                toVisitOnNext.addAll(neighbors)
            }
        }

        toVisit = toVisitOnNext

        ++count
    }

    return -1
}

fun main(args: Array<String>) {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val numbers = readln().split(" ").map { it.toInt() }.toIntArray()


    val bfsResult = bfs(numbers, k)
    println(bfsResult)
}
