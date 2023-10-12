fun main() {
    val n = readln().toInt()
    val meetings = List(n) {
        readln().split(" ").map { it.toInt() }.let { it[0] to it[1] }
    }

    var result = 0
    var currentEnd = 0

    meetings
        .sortedWith(compareBy<Pair<Int, Int>> { it.second }.thenBy { it.first })
        .forEach { (start, end) ->
            if (start >= currentEnd) {
                currentEnd = end
                ++result
            }
        }

    println(result)
}