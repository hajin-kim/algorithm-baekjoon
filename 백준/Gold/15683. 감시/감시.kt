val up = -1 to 0
val down = 1 to 0
val left = 0 to -1
val right = 0 to 1

val casesByType = mapOf(
    1 to listOf(
        listOf(up),
        listOf(down),
        listOf(left),
        listOf(right),
    ),
    2 to listOf(
        listOf(up, down),
        listOf(left, right),
    ),
    3 to listOf(
        listOf(up, right),
        listOf(right, down),
        listOf(down, left),
        listOf(left, up),
    ),
    4 to listOf(
        listOf(up, right, down),
        listOf(right, down, left),
        listOf(down, left, up),
        listOf(left, up, right),
    ),
    5 to listOf(
        listOf(up, right, down, left),
    ),
)

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val office = Array(n) {
        readln().split(" ").map { it.toInt() }.toIntArray()
    }

    val cctvs = mutableListOf<Pair<Int, Int>>()
    repeat(n) { r ->
        repeat(m) { c ->
            if (office[r][c] in 1..5)
                cctvs.add(r to c)
        }
    }

    fun isInRange(r: Int, c: Int) = r in 0 until n && c in 0 until m

    fun recurse(cctvs: List<Pair<Int, Int>>, office: Array<IntArray>): Int {
        if (cctvs.isEmpty())
            return office.sumOf { row -> row.count { it == 0 } }

        val (r, c) = cctvs.first()
        val cctvType = office[r][c]

        val cases = casesByType[cctvType]!!
        return cases.minOf { case ->
            val newOffice = office.map { it.copyOf() }.toTypedArray()

            case.forEach { (dr, dc) ->
                var (nr, nc) = r to c
                while (isInRange(nr, nc) && newOffice[nr][nc] != 6) {
                    if (newOffice[nr][nc] == 0)
                        newOffice[nr][nc] = -1
                    nr += dr
                    nc += dc
                }
            }

            recurse(cctvs.drop(1), newOffice)
        }
    }

    val result = recurse(cctvs, office)
    println(result)
}