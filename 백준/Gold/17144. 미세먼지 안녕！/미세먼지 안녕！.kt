fun solve(r: Int, c: Int, t: Int, grid: Array<IntArray>): Any {
    @Suppress("NAME_SHADOWING")
    var grid = grid

    val rPurifier = grid.indexOfFirst { it[0] == -1 }

    val rIndices = grid.indices
    val cIndices = grid[0].indices

    repeat(t) {
        // 1. diffusion
        val diffusedGrid = Array(r) { IntArray(c) }
        diffusedGrid[rPurifier][0] = -1
        diffusedGrid[rPurifier + 1][0] = -1

        for (i in rIndices) {
            for (j in cIndices) {
                if (grid[i][j] == -1)
                    continue

                val amount = grid[i][j] / 5
                val neighbors = listOf(
                    i to j + 1,
                    i to j - 1,
                    i + 1 to j,
                    i - 1 to j,
                )

                var count = 0
                for ((nr, nc) in neighbors) {
                    if (nr !in rIndices || nc !in cIndices || grid[nr][nc] == -1)
                        continue
                    ++count
                    diffusedGrid[nr][nc] += amount
                }
                diffusedGrid[i][j] += grid[i][j] - amount * count
            }
        }
        grid = diffusedGrid

        // 2. purification
        for (i in rPurifier - 1 downTo 1)
            grid[i][0] = grid[i - 1][0]
        for (j in 0 until c - 1)
            grid[0][j] = grid[0][j + 1]
        for (i in 0..rPurifier - 1)
            grid[i][c - 1] = grid[i + 1][c - 1]
        for (j in c - 1 downTo 2)
            grid[rPurifier][j] = grid[rPurifier][j - 1]
        grid[rPurifier][1] = 0

        for (i in rPurifier + 2..r - 2)
            grid[i][0] = grid[i + 1][0]
        for (j in 0 until c - 1)
            grid[r - 1][j] = grid[r - 1][j + 1]
        for (i in r - 1 downTo rPurifier + 2)
            grid[i][c - 1] = grid[i - 1][c - 1]
        for (j in c - 1 downTo 2)
            grid[rPurifier + 1][j] = grid[rPurifier + 1][j - 1]
        grid[rPurifier + 1][1] = 0
    }

    return grid.sumOf { it.sum() } + 2
}

fun main() {
    val (r, c, t) = readln().split(" ").map { it.toInt() }
    val grid = Array(r) {
        readln().split(" ").map { it.toInt() }.toIntArray()
    }

    val result = solve(r, c, t, grid)

    println(result)
}
