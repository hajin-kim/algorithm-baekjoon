fun drawMinimal(canvas: Array<CharArray>, r: Int, c: Int) {
    listOf(
        0 to 2,
        1 to 1, 1 to 3,
        2 to 0, 2 to 1, 2 to 2, 2 to 3, 2 to 4,
    ).forEach { (dr, dc) ->
        canvas[r + dr][c + dc] = '*'
    }
}

fun draw(canvas: Array<CharArray>, n: Int, r: Int, c: Int) {
    if (n == 3) {
        drawMinimal(canvas, r, c)
        return
    }

    val halfN = n / 2
    draw(canvas, halfN, r, c + halfN)
    draw(canvas, halfN, r + halfN, c)
    draw(canvas, halfN, r + halfN, c + halfN * 2)
}

fun solve(n: Int): Array<CharArray> {
    val canvas = Array(n) { CharArray(n * 2) { ' ' } }
    draw(canvas, n, 0, 0)
    return canvas
}

fun main() {
    val n = readln().toInt()

    val result = solve(n)

    println(result.joinToString("\n") { it.joinToString("") })
}