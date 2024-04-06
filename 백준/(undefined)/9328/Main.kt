import java.util.*

fun solve() {
    val (n, m) = readln().split(" ").map { it.toInt() }

    val map = (0 until n)
        .map { readln().toCharArray() }
        .toTypedArray()
    val initialKeys = readln()

    val foundKeys: MutableSet<Char> = initialKeys.toHashSet()
    val unopenedDoors = mutableMapOf<Char, MutableList<Pair<Int, Int>>>()

    val initialPoints = (0 until n).flatMap { listOf(it to 0, it to m - 1) } +
            (0 until m).flatMap { listOf(0 to it, n - 1 to it) }

    val queue = LinkedList<Pair<Int, Int>>().apply { addAll(initialPoints) }

    val visited = Array(n) { BooleanArray(m) }
    fun isInRange(y: Int, x: Int) = y in 0 until n && x in 0 until m

    var result = 0
    while (queue.isNotEmpty()) {
        val (y, x) = queue.poll()
        if (!isInRange(y, x) || visited[y][x]) continue

        val c = map[y][x]
        if (c == '*') continue

        if (c == '$') ++result
        else if (c.isLowerCase()) {
            foundKeys.add(c)
            unopenedDoors[c.uppercaseChar()]?.let { queue.addAll(it) }
        } else if (c.isUpperCase()) {
            if (c.lowercaseChar() !in foundKeys) {
                unopenedDoors.getOrPut(c) { mutableListOf() }.add(y to x)
                continue
            }
        }

        visited[y][x] = true

        listOf(
            y - 1 to x,
            y + 1 to x,
            y to x - 1,
            y to x + 1,
        ).let { queue.addAll(it) }
    }

    println(result)
}

fun main() {
    repeat(readln().toInt()) {
        solve()
    }
}
