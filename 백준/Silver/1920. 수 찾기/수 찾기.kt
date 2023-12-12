fun solve(ns: Set<Int>, ms: List<Int>): List<Boolean> {
    return ms.map { it in ns }
}

fun main() {
    val n = readln().toInt()
    val ns = readln().split(" ").map { it.toInt() }.toSet()
    val m = readln().toInt()
    val ms = readln().split(" ").map { it.toInt() }

    val result = solve(ns, ms)

    println(result.joinToString("\n") { if (it) "1" else "0" })
}