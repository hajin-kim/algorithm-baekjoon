import java.util.PriorityQueue

fun main() {
    val n = readln().toInt()
    val table = Array(n) { readln().split(" ").map { it.toInt() }.toIntArray() }

    val result = solve(n, table)

    println(result)
}

fun solve(n: Int, table: Array<IntArray>): Int {
    val priorityQueue = PriorityQueue<Pair<Int, Int>>(n, compareByDescending { (r, c) -> table[r][c] })

    priorityQueue += (0 until n).map { n - 1 to it }

    var result = 0
    repeat(n) {
        val (r, c) = priorityQueue.poll()

        result = table[r][c]
        if (r - 1 >= 0)
            priorityQueue += r - 1 to c
    }

    return result
}
