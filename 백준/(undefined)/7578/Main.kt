class SegmentTree(private val size: Int) {
    private val tree = IntArray(size * 4)

    fun update(index: Int, value: Int) {
        update(1, 0, size - 1, index, value)
    }

    private fun update(node: Int, start: Int, end: Int, index: Int, value: Int) {
        if (index < start || index > end)
            return

        tree[node] += value

        if (start != end) {
            val mid = (start + end) / 2
            update(node * 2, start, mid, index, value)
            update(node * 2 + 1, mid + 1, end, index, value)
        }
    }

    fun query(left: Int, right: Int): Int {
        return query(1, 0, size - 1, left, right)
    }

    private fun query(node: Int, start: Int, end: Int, left: Int, right: Int): Int {
        if (left > end || right < start)
            return 0

        if (left <= start && end <= right)
            return tree[node]

        val mid = (start + end) / 2
        return query(node * 2, start, mid, left, right) + query(node * 2 + 1, mid + 1, end, left, right)
    }

}

fun main(args: Array<String>) {
    val n = readln().toInt()
    val firstPositions = readln().split(" ").map { it.toInt() }
    val secondPositions = readln().split(" ").map { it.toInt() }

    val indexBySecondPosition = secondPositions.withIndex().associate { it.value to it.index }
    val orders = firstPositions.map { indexBySecondPosition[it]!! }

    val segmentTree = SegmentTree(n)

    val result = orders.fold(0L) { acc, it ->
        segmentTree.update(it, 1)
        val nInversionByIt = it + 1 - segmentTree.query(0, it)
        acc + nInversionByIt
    }

    println(result)
}