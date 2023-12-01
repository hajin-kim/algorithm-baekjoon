fun dfs(r: Int, c: Int, board: Array<CharArray>, visited: BooleanArray): Int {
    if (r !in board.indices || c !in board[0].indices)
        return 0

    val code = board[r][c].code
    if (visited[code])
        return 0

    visited[code] = true

    val result = 1 + listOf(
        r + 1 to c,
        r - 1 to c,
        r to c + 1,
        r to c - 1,
    ).maxOf { (nr, nc) ->
        dfs(nr, nc, board, visited)
    }

    visited[code] = false

    return result
}

fun solve(r: Int, c: Int, board: Array<CharArray>): Int {
    val visited = BooleanArray('Z'.code + 1)

    return dfs(0, 0, board, visited)
}

fun main() {
    val (r, c) = readln().split(" ").map { it.toInt() }
    val board = Array(r) { readln().toCharArray() }

    val result = solve(r, c, board)

    println(result)
}