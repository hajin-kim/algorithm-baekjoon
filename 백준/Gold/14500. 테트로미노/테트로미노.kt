fun Array<BooleanArray>.rotateTetromino(): Array<BooleanArray> {
    val n = size
    val m = this[0].size
    val rotated = Array(m) { BooleanArray(n) }

    for (i in 0 until n) {
        for (j in 0 until m) {
            rotated[j][n - i - 1] = this[i][j]
        }
    }

    return rotated
}

fun Array<BooleanArray>.reverseTetromino(): Array<BooleanArray> {
    val n = size
    val m = this[0].size
    val reversed = Array(n) { BooleanArray(m) }

    for (i in 0 until n) {
        reversed[i] = this[i].reversedArray()
    }

    return reversed
}

fun listTetrominoesAllowingDuplication(): List<Array<BooleanArray>> {
    val baseTetrominoes = listOf(
        arrayOf(
            booleanArrayOf(true, true, true, true),
        ),
        arrayOf(
            booleanArrayOf(true, true),
            booleanArrayOf(true, true),
        ),
        arrayOf(
            booleanArrayOf(true, true),
            booleanArrayOf(true, false),
            booleanArrayOf(true, false),
        ),
        arrayOf(
            booleanArrayOf(true, false),
            booleanArrayOf(true, true),
            booleanArrayOf(false, true),
        ),
        arrayOf(
            booleanArrayOf(true, false),
            booleanArrayOf(true, true),
            booleanArrayOf(true, false),
        ),
    )

    var rotated = baseTetrominoes
    repeat(3) {
        rotated = baseTetrominoes + rotated.map { it.rotateTetromino() }
    }

    return rotated + rotated.map { it.reverseTetromino() }
}

fun sum(grid: Array<IntArray>, tetromino: Array<BooleanArray>, r: Int, c: Int): Int {
    var sum = 0
    for (nr in 0 until (tetromino.size)) {
        for (nc in 0 until (tetromino[0].size)) {
            if (tetromino[nr][nc])
                sum += grid[r + nr][c + nc]
        }
    }
    return sum
}

fun solve(n: Int, m: Int, grid: Array<IntArray>): Any {
    var result = 0
    val tetrominoes = listTetrominoesAllowingDuplication()
    for (tetromino in tetrominoes) {
        for (r in 0..(n - tetromino.size)) {
            for (c in 0..(m - tetromino[0].size)) {
                val sum = sum(grid, tetromino, r, c)
                result = maxOf(result, sum)
            }
        }
    }
    return result
}

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val grid = Array(n) {
        readln().split(" ").map { it.toInt() }.toIntArray()
    }

    val result = solve(n, m, grid)

    println(result)
}
