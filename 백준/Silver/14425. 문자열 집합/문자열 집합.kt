fun solve(s: Set<String>, queries: List<String>): Int {
    return queries.count { it in s }
}

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val s = Array(n) { readln() }.toSet()
    val queries = List(m) { readln() }

    val result = solve(s, queries)

    println(result)
}