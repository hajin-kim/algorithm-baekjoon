fun main() {
    val n = readln().toInt()
    val k = readln().toInt()
    val sensors = readln().split(" ").map { it.toInt() }.toIntArray()

    val result = solve(n, k, sensors)

    println(result)
}

fun solve(n: Int, k: Int, sensors: IntArray): Any {
    sensors.sort()

    val totalDistance = sensors.last() - sensors.first()

    val diffs = sensors
        .toList()
        .zipWithNext()
        .map { (a, b) -> b - a }
        .toIntArray()
    diffs.sort()

    return totalDistance - diffs.takeLast(k - 1).sum()
}
