fun main(args: Array<String>) {
    val n = readln().toInt()
    val sortedWeights = readln()
        .split(" ")
        .map { it.toInt() }
        .sorted()

    var currentMax = 0
    for (weight in sortedWeights) {
        if (currentMax + 1 < weight)
            break
        currentMax += weight
    }

    println(currentMax + 1)
}