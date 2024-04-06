// https://www.acmicpc.net/problem/2042

class SegmentTree(numbers: LongArray) {
    private val tree = LongArray(numbers.size * 4)
    private val rootRangeStart = 0
    private val rootRangeEnd = numbers.size - 1
    private val rootIndex = 1
    private val identity = 0L

    init {
        init(numbers, rootRangeStart, rootRangeEnd, rootIndex)
    }

    private fun init(numbers: LongArray, start: Int, end: Int, node: Int): Long {
        if (start == end) {
            tree[node] = numbers[start]
            return tree[node]
        }

        val mid = (start + end) / 2
        tree[node] = init(numbers, start, mid, node * 2) + init(numbers, mid + 1, end, node * 2 + 1)
        return tree[node]
    }

    fun sum(start: Int, end: Int): Long = sum(rootRangeStart, rootRangeEnd, rootIndex, start, end)

    private fun sum(currentNodeStart: Int, currentNodeEnd: Int, node: Int, targetStart: Int, targetEnd: Int): Long {
        if (targetEnd < currentNodeStart || targetStart > currentNodeEnd) {
            return identity
        }

        if (targetStart <= currentNodeStart && currentNodeEnd <= targetEnd) {
            return tree[node]
        }

        val mid = (currentNodeStart + currentNodeEnd) / 2
        return sum(currentNodeStart, mid, node * 2, targetStart, targetEnd) + sum(
            mid + 1,
            currentNodeEnd,
            node * 2 + 1,
            targetStart,
            targetEnd
        )
    }

    fun update(index: Int, diffToAdd: Long) = update(rootRangeStart, rootRangeEnd, rootIndex, index, diffToAdd)

    private fun update(currentNodeStart: Int, currentNodeEnd: Int, node: Int, index: Int, diff: Long) {
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

fun main(args: Array<String>) {
    val (n, m, k) = readln().split(" ").map { it.toInt() }
    val numbers = (1..n)
        .map { readln().toLong() }
        .toLongArray()
    val commands = (1..m + k)
        .map { readln().split(" ").map { it.toLong() } }

    val segmentTree = SegmentTree(numbers)

    commands.forEach {
        val (a, b, c) = it
        when (a) {
            1L -> {
                val targetIndex = b.toInt() - 1
                val diff = c - numbers[targetIndex]
                numbers[targetIndex] = c
                segmentTree.update(targetIndex, diff)
            }

            2L -> println(segmentTree.sum(b.toInt() - 1, c.toInt() - 1))
        }
    }
}
