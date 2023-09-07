fun main(args: Array<String>) {
    val n = readln().toInt()
    val surfaces = readln().split(" ").map { it.toLong() }

    if (n == 1) {
        println(surfaces.sorted().take(5).sum())
        return
    }

    val (first, second, third) = listOf(
        minOf(surfaces[0], surfaces[5]),
        minOf(surfaces[1], surfaces[4]),
        minOf(surfaces[2], surfaces[3]),
    )
        .sorted()

    var result = 0L

    result += (first + second + third) * 4
    result += (first + second) * 4 * (n - 1)
    result += (first + second) * 4 * (n - 2)
    result += (first) * 4 * (n - 1) * (n - 2)
    result += (first) * (n - 2) * (n - 2)

    println(result)
}