@file:OptIn(ExperimentalUnsignedTypes::class)

fun getCandidates(currentN: Int): List<Triple<Int, Int, Int>> {
    val candidates = mutableListOf<Triple<Int, Int, Int>>()

    val shareOne = listOf(
        Triple(currentN, 0, 0),
        Triple(0, currentN, 0),
        Triple(0, 0, currentN),
    )
    candidates.addAll(shareOne)

    if (currentN % 2 == 0) {
        val shareTwo = listOf(
            Triple(currentN / 2, currentN / 2, 0),
            Triple(currentN / 2, 0, currentN / 2),
            Triple(0, currentN / 2, currentN / 2),
        )
        candidates.addAll(shareTwo)
    }

    if (currentN % 3 == 0) {
        val shareThree = Triple(currentN / 3, currentN / 3, currentN / 3)
        candidates.add(shareThree)
    }

    return candidates
}

private fun initRgbArray(r: Int, g: Int, b: Int) = Array(r + 1) { Array(g + 1) { LongArray(b + 1) } }

val factorialCache = ULongArray(51)

fun Int.factorial(): ULong {
    if (this <= 1)
        return 1UL

    if (factorialCache[this] != 0UL)
        return factorialCache[this]

    val result = (this - 1).factorial() * this.toUInt()
    factorialCache[this] = result

    return result
}

fun permutation(r: Int, g: Int, b: Int): Long {
    val currentN = r + g + b
    return (currentN.factorial() / (r.factorial() * g.factorial() * b.factorial())).toLong()
}

fun solve(n: Int, r: Int, g: Int, b: Int): Long {
    var prev = initRgbArray(r, g, b)
    if (r > 0) prev[1][0][0] = 1
    if (g > 0) prev[0][1][0] = 1
    if (b > 0) prev[0][0][1] = 1

    for (currentN in 2..n) {
        val now = initRgbArray(r, g, b)

        val candidates = getCandidates(currentN)

        for ((dr, dg, db) in candidates) {
            for (nr in dr..r) {
                for (ng in dg..g) {
                    for (nb in db..b) {
                        now[nr][ng][nb] += prev[nr - dr][ng - dg][nb - db] * permutation(dr, dg, db)
                    }
                }
            }
        }

        prev = now
    }

    return prev.sumOf { gs -> gs.sumOf { bs -> bs.sum() } }
}

fun main() {
    val (n, r, g, b) = readln().split(" ").map { it.toInt() }

    val result = solve(n, r, g, b)

    println(result)
}