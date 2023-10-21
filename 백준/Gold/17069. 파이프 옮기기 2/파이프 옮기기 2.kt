class Solve(private val n: Int, private val grid: Array<IntArray>) {
    private fun isInRange(r: Int, c: Int): Boolean = r in 0 until n && c in 0 until n

    private fun isEmpty(r: Int, c: Int): Boolean = grid[r][c] == 0

    private fun isValidToOccupy(r: Int, c: Int): Boolean = isInRange(r, c) && isEmpty(r, c)

    fun solve(): Long {
        // direction 0: right
        // direction 1: down-right
        // direction 2: down
        val cache = Array(n) { Array(n) { LongArray(3) } }
        cache[0][1][0] = 1

        for (r in 0 until n) {
            for (c in 1 until n) {
                if (!isValidToOccupy(r, c))
                    continue

                val prevR = r - 1
                val prevC = c - 1
                var shouldCheckAntiDiagonal = true

                if (isValidToOccupy(r, prevC))
                    cache[r][c][0] += cache[r][prevC][0] + cache[r][prevC][1]
                else
                    shouldCheckAntiDiagonal = false

                if (isValidToOccupy(prevR, c))
                    cache[r][c][2] += cache[prevR][c][2] + cache[prevR][c][1]
                else
                    shouldCheckAntiDiagonal = false

                if (shouldCheckAntiDiagonal && isValidToOccupy(prevR, prevC))
                    cache[r][c][1] += cache[prevR][prevC].sum()
            }
        }

        return cache[n - 1][n - 1].sum()
    }
}

fun main() {
    val n = readln().toInt()
    val grid = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }

    val result = Solve(n, grid).solve()

    println(result)
}