import java.util.LinkedList

fun solve(n: Int, k: Int): List<Int> {
    val result = mutableListOf<Int>()
    val origin = LinkedList((1..n).toList())
    var iterator = origin.iterator()

    var skipped = 0
    while (origin.isNotEmpty()) {
        if (!iterator.hasNext())
            iterator = origin.iterator()

        val now = iterator.next()
        skipped++

        if (skipped == k) {
            iterator.remove()
            result.add(now)
            skipped = 0
        }
    }
    return result
}

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }

    val result = solve(n, k)

    println(result.joinToString(separator = ", ", prefix = "<", postfix = ">"))
}