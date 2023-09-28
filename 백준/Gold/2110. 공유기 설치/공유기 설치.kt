fun main() {
    val (n, c) = readln().split(" ").map { it.toInt() }
    val xs = List(n) { readln().toInt() }.sorted()

    val distances = xs.zipWithNext { a, b -> b - a }

    var low = 0
    var high = xs.last()
    while (low <= high) {
        val currentLengthLimit = (low + high) / 2

        var count = 1
        var distance = 0
        distances.forEach {
            distance += it

            if (distance >= currentLengthLimit) {
                distance = 0
                count++
            }
        }

        if (count >= c)
            low = currentLengthLimit + 1
        else
            high = currentLengthLimit - 1
    }

    println(high)
}