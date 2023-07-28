fun main(args: Array<String>) {
    val n = readln().toInt()
    val rawNumberPairs = (1..n)
        .map {
            it to readln().toInt()
        }
        .toMutableList()

    var somethingRemoved: Boolean
    do {
        somethingRemoved = false
        val (firstNumbers, secondNumbers) = rawNumberPairs.unzip()

        val firstsToRemove = firstNumbers.filter { it !in secondNumbers }
        if (firstsToRemove.isNotEmpty()) {
            somethingRemoved = true
            rawNumberPairs.removeAll { (first, _) -> first in firstsToRemove }
        }

        val secondsToRemove = secondNumbers.filter { it !in firstNumbers }
        if (secondsToRemove.isNotEmpty()) {
            somethingRemoved = true
            rawNumberPairs.removeAll { (_, second) -> second in secondsToRemove }
        }
    } while (somethingRemoved)

    println(rawNumberPairs.size)
    println(rawNumberPairs.map { it.first }.joinToString("\n"))
}
