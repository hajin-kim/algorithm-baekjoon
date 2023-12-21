fun solve(unheard: Set<String>, unseen: Set<String>): List<String> {
    return unheard.intersect(unseen).sorted()
}

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val unheard = List(n) {
        readln()
    }.toSet()

    val unseen = List(m) {
        readln()
    }.toSet()

    val result = solve(unheard, unseen)

    println(result.size)
    println(result.joinToString("\n"))
}