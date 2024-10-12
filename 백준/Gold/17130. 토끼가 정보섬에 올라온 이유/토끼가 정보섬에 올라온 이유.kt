fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val board = Array(n) { readln() }

    val result = solve(n, m, board)

    println(result)
}

fun solve(n: Int, m: Int, board: Array<String>): Int {
    var result = -1

    val maxCarrots = Array(n) { IntArray(m) { -1 } }

    for (x in 0 until m) {
        for (y in 0 until n) {
            val now = board[y][x]

            if (now == '#')
                continue

            if (now == 'R') {
                maxCarrots[y][x] = 0
                continue
            }

            if (x > 0) {
                val leftNeighbors = listOf(
                    x - 1 to y - 1,
                    x - 1 to y,
                    x - 1 to y + 1,
                )

                for ((nx, ny) in leftNeighbors) {
                    if (ny !in 0 until n || nx !in 0 until m)
                        continue
                    maxCarrots[y][x] = maxOf(maxCarrots[y][x], maxCarrots[ny][nx])
                }
            }

            if (maxCarrots[y][x] == -1)
                continue

            if (now == 'C')
                maxCarrots[y][x] += 1
            else if (now == 'O')
                result = maxOf(result, maxCarrots[y][x])
        }
    }

    return result
}
