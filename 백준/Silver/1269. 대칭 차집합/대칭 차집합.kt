fun solve(a: Set<Int>, b: Set<Int>): Int {
    val symmetricDifference = (a - b) + (b - a)
    return symmetricDifference.size
}

fun main() {
    readln()
    val a = readln().split(" ").map { it.toInt() }.toSet()
    val b = readln().split(" ").map { it.toInt() }.toSet()

    val result = solve(a, b)

    println(result)
}