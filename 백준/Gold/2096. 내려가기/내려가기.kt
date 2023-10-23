fun solve(n: Int, lines: List<Triple<Int, Int, Int>>, f: (ints: IntArray) -> Int): Int {
    return lines.fold(Triple(0, 0, 0)) { acc: Triple<Int, Int, Int>, line: Triple<Int, Int, Int> ->
        val first = f(
            intArrayOf(
                acc.first,
                acc.second,
            )
        ) + line.first

        val second = f(
            intArrayOf(
                acc.first,
                acc.second,
                acc.third,
            )
        ) + line.second

        val third = f(
            intArrayOf(
                acc.second,
                acc.third,
            )
        ) + line.third

        Triple(first, second, third)
    }
        .let { f(intArrayOf(it.first, it.second, it.third)) }
}

fun solveMax(n: Int, lines: List<Triple<Int, Int, Int>>): Int =
    solve(n, lines, IntArray::max)

fun solveMin(n: Int, lines: List<Triple<Int, Int, Int>>): Int =
    solve(n, lines, IntArray::min)


fun main() {
    val n = readln().toInt()
    val lines = List(n) { readln().split(" ").map { it.toInt() }.let { Triple(it[0], it[1], it[2]) } }

    val max = solveMax(n, lines)
    val min = solveMin(n, lines)

    println("$max $min")
}