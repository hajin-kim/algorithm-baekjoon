import java.util.*

class Solution(
    private val givenK: Int,
    private val nColumns: Int,
    private val nRows: Int,
    private val isWall: Array<BooleanArray>,
) {
    private val UNVISITED = 1000000000

    private fun isInRange(r: Int, c: Int): Boolean =
        r in 0 until nRows && c in 0 until nColumns

    fun solve(): Int {
        if (nColumns == 1 && nRows == 1)
            return 0

        val queue: Queue<Triple<Int, Int, Int>> = ArrayDeque(nRows * nColumns * (givenK + 1))
        queue.add(Triple(0, 0, givenK))

        val distances = Array(nRows) { Array(nColumns) { IntArray(givenK + 1) { UNVISITED } } }
        distances[0][0][givenK] = 0
        var nextDistance = 1

        while (queue.isNotEmpty()) {
            repeat(queue.size) {
                val (r, c, remainingK) = queue.poll()

                listOf(
                    Triple(r + 1, c, remainingK),
                    Triple(r - 1, c, remainingK),
                    Triple(r, c + 1, remainingK),
                    Triple(r, c - 1, remainingK),
                    Triple(r + 1, c + 2, remainingK - 1),
                    Triple(r + 1, c - 2, remainingK - 1),
                    Triple(r - 1, c + 2, remainingK - 1),
                    Triple(r - 1, c - 2, remainingK - 1),
                    Triple(r + 2, c + 1, remainingK - 1),
                    Triple(r + 2, c - 1, remainingK - 1),
                    Triple(r - 2, c + 1, remainingK - 1),
                    Triple(r - 2, c - 1, remainingK - 1),
                ).forEach { next ->
                    val (nr, nc, nk) = next

                    if (!isInRange(nr, nc)
                        || nk < 0
                        || isWall[nr][nc]
                        || distances[nr][nc][nk] != UNVISITED
                    )
                        return@forEach

                    if (nr == nRows - 1 && nc == nColumns - 1)
                        return nextDistance

                    distances[nr][nc][nk] = nextDistance
                    queue.add(next)
                }
            }

            ++nextDistance
        }

        return -1
    }
}

fun main() {
    val k = readln().toInt()
    val (w, h) = readln().split(" ").map { it.toInt() }
    val isWall = Array(h) {
        readln().split(" ").map { it == "1" }.toBooleanArray()
    }

    val result = Solution(givenK = k, nColumns = w, nRows = h, isWall = isWall).solve()

    println(result)
}