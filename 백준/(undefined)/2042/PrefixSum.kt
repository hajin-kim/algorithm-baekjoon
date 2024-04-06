// https://www.acmicpc.net/problem/2042

fun main(args: Array<String>) {
    val (n, m, k) = readln().split(" ").map { it.toInt() }
    val numbers = (1..n)
        .map { readln().toLong() }
        .toLongArray()
    val commands = (1..m + k)
        .map { readln().split(" ").map { it.toLong() } }

    val prefixSum = numbers
        .scan(0L) { acc, i -> acc + i }
        .toLongArray()

    fun querySum(start: Int, endInclusive: Int): Long {
        return prefixSum[endInclusive + 1] - prefixSum[start]
    }

    val changedValues = mutableMapOf<Int, Long>()

    commands.forEach {
        val (command, a, b) = it
        when (command) {
            1L -> {
                changedValues[a.toInt() - 1] = b - numbers[a.toInt() - 1]
            }

            2L -> {
                val start = a.toInt() - 1
                val endInclusive = b.toInt() - 1
                var sum = querySum(start, endInclusive)
                changedValues.forEach { (index, value) ->
                    if (index in start..endInclusive) {
                        sum += value
                    }
                }
                println(sum)
            }
        }
    }
}
