val DEFAULT = Int.MAX_VALUE to -1

class SegmentTree(private val size: Int) {
    private val tree = Array(size * 4) { DEFAULT }

    private fun getMinMax(a: Pair<Int, Int>, b: Pair<Int, Int>): Pair<Int, Int> {
        return minOf(a.first, b.first) to maxOf(a.second, b.second)
    }

    fun update(index: Int, value: Pair<Int, Int>) {
        update(1, 0, size - 1, index, value)
    }

    private fun update(node: Int, start: Int, end: Int, index: Int, value: Pair<Int, Int>) {
        if (index < start || index > end)
            return

        tree[node] = getMinMax(tree[node], value)

        if (start != end) {
            val mid = (start + end) / 2
            update(node * 2, start, mid, index, value)
            update(node * 2 + 1, mid + 1, end, index, value)
        }
    }

    fun query(left: Int, right: Int): Pair<Int, Int> {
        return query(1, 0, size - 1, left, right)
    }

    private fun query(node: Int, start: Int, end: Int, left: Int, right: Int): Pair<Int, Int> {
        if (left > end || right < start)
            return DEFAULT

        if (left <= start && end <= right)
            return tree[node]

        val mid = (start + end) / 2

        val leftResult = query(node * 2, start, mid, left, right)
        val rightResult = query(node * 2 + 1, mid + 1, end, left, right)

        return getMinMax(leftResult, rightResult)
    }

}

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val numbers = (1..n).map { readln().toInt() }
    val queries = (1..m).map {
        readln().split(" ").let { it[0].toInt() to it[1].toInt() }
    }

    val segmentTree = SegmentTree(n)
    numbers.forEachIndexed { index, i ->
        segmentTree.update(index, i to i)
    }
    val results = queries.map {
        segmentTree.query(it.first - 1, it.second - 1)
    }
    val resultString = results.joinToString("\n") { "${it.first} ${it.second}" }

    println(resultString)
}
