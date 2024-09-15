fun main() {
    var board = readln()
    while (board != "end") {
        val result = solve(board)

        println(if (result) "valid" else "invalid")

        board = readln()
    }
}

fun Char.winsBingo(board: String): Boolean {
    val cases = arrayOf(
        intArrayOf(0, 1, 2),
        intArrayOf(3, 4, 5),
        intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6),
        intArrayOf(1, 4, 7),
        intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8),
        intArrayOf(2, 4, 6),
    )

    for (case in cases) {
        if (case.all { board[it] == this })
            return true
    }

    return false
}

fun solve(board: String): Boolean {
    val xCount = board.count { it == 'X' }
    val oCount = board.count { it == 'O' }

    val xWon = 'X'.winsBingo(board)
    val oWon = 'O'.winsBingo(board)

    if (xWon && oWon)
        return false

    if (xWon)
        return xCount == oCount + 1

    if (oWon)
        return xCount == oCount

    return xCount == 5 && oCount == 4
}
