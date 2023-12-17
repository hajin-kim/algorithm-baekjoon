const val EMPTY = 0

fun solve(n: Int, students: Array<Pair<Int, IntArray>>): Int {
    val chairs = Array(n) { IntArray(n) }
    val range = 0 until n

    fun getNeighbors(r: Int, c: Int) = listOf(
        r to c + 1,
        r to c - 1,
        r + 1 to c,
        r - 1 to c,
    ).filter { (nr, nc) -> nr in range && nc in range }

    for (i in students.indices) {
        val (student, friends) = students[i]

        var maxR = 0
        var maxC = 0
        var maxFriends = -1
        var maxEmpties = -1

        for (r in range) {
            for (c in range) {
                if (chairs[r][c] != EMPTY)
                    continue

                val neighbors = getNeighbors(r, c)

                val nFriends = neighbors
                    .count { (nr, nc) -> chairs[nr][nc] in friends }
                val nEmpties = neighbors
                    .count { (nr, nc) -> chairs[nr][nc] == EMPTY }

                if (nFriends > maxFriends) {
                    maxFriends = nFriends
                    maxEmpties = nEmpties
                    maxR = r
                    maxC = c
                }
                if (nFriends < maxFriends)
                    continue

                if (nEmpties > maxEmpties) {
                    maxFriends = nFriends
                    maxEmpties = nEmpties
                    maxR = r
                    maxC = c
                }
            }
        }

        chairs[maxR][maxC] = student
    }

    val score = range.sumOf { r ->
        range.sumOf { c ->
            val student = chairs[r][c]
            val friends = students
                .first { it.first == student }
                .second

            val neighbors = getNeighbors(r, c)
            val nFriends = neighbors.count { (r, c) -> chairs[r][c] in friends }

            Math.pow(10.0, nFriends.toDouble()).toInt() / 10
        }
    }

    return score
}

fun main() {
    val n = readln().toInt()
    val students = Array(n * n) {
        readln().split(" ").map { it.toInt() }.let { it[0] to it.drop(1).toIntArray() }
    }

    val result = solve(n, students)

    println(result)
}