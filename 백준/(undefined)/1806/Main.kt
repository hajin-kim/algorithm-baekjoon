fun main() {
    val (n, s) = readln().split(" ").map { it.toInt() }
    val sequence = readln().split(" ").map { it.toInt() }.toIntArray()

    var rangeStart = 0
    var sum = 0
    var shortestLength = Int.MAX_VALUE
    sequence.forEachIndexed { index, it ->
        sum += it
        while (sum - sequence[rangeStart] >= s) {
            sum -= sequence[rangeStart++]
        }
        if (sum >= s)
            shortestLength = minOf(shortestLength, index - rangeStart + 1)
    }

    println(if (shortestLength == Int.MAX_VALUE) 0 else shortestLength)
}