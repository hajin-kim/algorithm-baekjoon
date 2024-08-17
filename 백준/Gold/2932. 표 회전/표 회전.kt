fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val movements = Array(k) {
        readln().split(" ").map { it.toInt() }.let { Triple(it[0], it[1], it[2]) }
    }

    val result = solve(n, k, movements)

    println(result.joinToString("\n"))
}

fun solve(n: Int, k: Int, movements: Array<Triple<Int, Int, Int>>): IntArray {
    val result = IntArray(k)

    val indicesArray = movements.map { (x, _, _) ->
        val r = (x - 1) / n
        val c = (x - 1) % n
        intArrayOf(r, c)
    }.toTypedArray()

    repeat(k) { i ->
        val (_, oneBasedTargetR, oneBasedTargetC) = movements[i]
        val (currentR, currentC) = indicesArray[i]

        val movementHorizontal = ((oneBasedTargetC - 1) + n - currentC) % n
        val movementVertical = ((oneBasedTargetR - 1) + n - currentR) % n

        for (indices in indicesArray) {
            val (r, c) = indices
            if (r == currentR)
                indices[1] = (c + movementHorizontal) % n
        }

        for (indices in indicesArray) {
            val (r, c) = indices
            if (c == oneBasedTargetC - 1)
                indices[0] = (r + movementVertical) % n
        }

        result[i] = movementHorizontal + movementVertical
    }

    return result
}
