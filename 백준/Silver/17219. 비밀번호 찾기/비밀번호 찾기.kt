fun solve(passwords: Array<List<String>>, queries: Array<String>): List<String> {
    val passwordMap = passwords.associate { (site, password) -> site to password }
    return queries.map { passwordMap[it]!! }
}

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val passwords = Array(n) {
        readln().split(" ")
    }
    val queries = Array(m) {
        readln()
    }


    val result = solve(passwords, queries)

    println(result.joinToString("\n"))
}