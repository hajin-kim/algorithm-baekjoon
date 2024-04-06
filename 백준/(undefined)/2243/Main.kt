const val MAX_SIZE = 1000000

class SegmentTree {
    private val tree = IntArray(MAX_SIZE * 4)
    private val rootRangeStart = 0
    private val rootRangeEnd = MAX_SIZE
    private val rootIndex = 1

    fun indexOfNthOne(n: Int): Int = indexOfNthOne(rootRangeStart, rootRangeEnd, rootIndex, n)

    private fun indexOfNthOne(currentNodeStart: Int, currentNodeEnd: Int, node: Int, n: Int): Int {
        if (currentNodeStart == currentNodeEnd) {
            return currentNodeStart
        }

        val mid = (currentNodeStart + currentNodeEnd) / 2
        return if (tree[node * 2] >= n) {
            indexOfNthOne(currentNodeStart, mid, node * 2, n)
        } else {
            indexOfNthOne(mid + 1, currentNodeEnd, node * 2 + 1, n - tree[node * 2])
        }
    }

    fun update(index: Int, diffToAdd: Int) = update(rootRangeStart, rootRangeEnd, rootIndex, index, diffToAdd)

    private fun update(currentNodeStart: Int, currentNodeEnd: Int, node: Int, index: Int, diff: Int) {
        if (index < currentNodeStart || index > currentNodeEnd) {
            return
        }

        tree[node] += diff

        if (currentNodeStart == currentNodeEnd) {
            return
        }

        val mid = (currentNodeStart + currentNodeEnd) / 2
        update(currentNodeStart, mid, node * 2, index, diff)
        update(mid + 1, currentNodeEnd, node * 2 + 1, index, diff)
    }
}

fun main() {
    val n = readln().toInt()

    val segmentTree = SegmentTree()
    val results = mutableListOf<Int>()

    (1..n).forEach {
        val inputs = readln().split(" ").map { it.toInt() }
        when (inputs[0]) {
            1 -> {
                val indexOfNthOne = segmentTree.indexOfNthOne(inputs[1])
                results.add(indexOfNthOne)
                segmentTree.update(indexOfNthOne, -1)
            }

            2 -> {
                segmentTree.update(inputs[1], inputs[2])
            }
        }
    }

    println(results.joinToString("\n"))
}