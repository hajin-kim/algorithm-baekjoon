fun main() {
    val c = readln().toInt()
    repeat(c) {
        val n = readln().toInt()
        val choices = readln().split(" ").map { it.toInt() - 1 }.toIntArray()

        val visitedAt = IntArray(n)
        val distances = IntArray(n)

        tailrec fun countCycleLength(node: Int, now: Int, distance: Int): Int {
            if (visitedAt[node] == now)
                return distance - distances[node]
            if (visitedAt[node] != 0)
                return 0

            visitedAt[node] = now
            distances[node] = distance
            return countCycleLength(choices[node], now, distance + 1)
        }

        val nChosen = (0 until n).fold(0) { acc, i ->
            acc + countCycleLength(i, i + 1, 0)
        }

        println(n - nChosen)
    }
}