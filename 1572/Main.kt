import java.util.*

const val MAX_SIZE = 65536

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
    val (n, k) = readln().split(" ").map { it.toInt() }

    val midPosition = (k + 1) / 2

    val windowQueue: Queue<Int> = LinkedList()
    val segmentTree = SegmentTree()
    var result = 0L
    repeat(n) {
        val num = readln().toInt()
        windowQueue.add(num)

        segmentTree.update(num, 1)
        if (it >= k - 1) {
            val mid = segmentTree.indexOfNthOne(midPosition)
            result += mid

            val shouldDrop = windowQueue.poll()
            segmentTree.update(shouldDrop, -1)
        }
    }
    println(result)
}