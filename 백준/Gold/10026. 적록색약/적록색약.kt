fun main() {
    val n = readln().toInt()
    val colors = Array(n) { readln().toCharArray() }


    fun dfs(visitedIds: Array<IntArray>, id: Int, targetColors: List<Char>, y: Int, x: Int) {
        if (y !in 0 until n || x !in 0 until n)
            return
        if (visitedIds[y][x] != -1)
            return
        if (colors[y][x] !in targetColors)
            return

        visitedIds[y][x] = id

        listOf(
            y - 1 to x,
            y + 1 to x,
            y to x - 1,
            y to x + 1,
        ).forEach { (ny, nx) ->
            dfs(visitedIds, id, targetColors, ny, nx)
        }
    }

    val visitedIds1 = Array(n) { IntArray(n) { -1 } }
    var id1 = 0
    (0 until n).forEach { y ->
        (0 until n).forEach { x ->
            if (visitedIds1[y][x] == -1) {
                dfs(visitedIds1, id1++, listOf(colors[y][x]), y, x)
            }
        }
    }

    val visitedIds2 = Array(n) { IntArray(n) { -1 } }
    var id2 = 0
    (0 until n).forEach { y ->
        (0 until n).forEach { x ->
            if (visitedIds2[y][x] == -1) {
                val targetColors = when (colors[y][x]) {
                    'R', 'G' -> listOf('R', 'G')
                    else -> listOf('B')
                }
                dfs(visitedIds2, id2++, targetColors, y, x)
            }
        }
    }

    println("$id1 $id2")
}
