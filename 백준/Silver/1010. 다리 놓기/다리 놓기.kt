fun combination(n: Int, r: Int): Long {
    if (r !in 0..n)
        return 0
    if (r == 0 || r == n)
        return 1
    return combination(n, r - 1) * (n - r + 1) / r
}

fun main() {
    val t = readln().toInt()
    repeat(t) {
        val (n, m) = readln().split(" ").map { it.toInt() }

        val result = combination(m, n)

        println(result)
    }
}