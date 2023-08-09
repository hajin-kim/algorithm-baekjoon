import java.util.*

fun main(args: Array<String>) {
    val n = readln().toInt()
    val (firsts, seconds) = (1..n)
        .map {
            readln()
                .split(" ")
                .let { it[0].toInt() to it[1].toInt() }
        }
        .sortedBy { it.first }
        .toTypedArray()
        .unzip()

    val minValuesWhereIndexAsLength = IntArray(n) { Int.MAX_VALUE }
    val minValueIndices = IntArray(n)
    val parents = IntArray(n) { -1 }

    var maxLength = 0
    seconds.forEachIndexed { index, i ->
        var foundLength = minValuesWhereIndexAsLength.binarySearch(i)
        var notFound = false
        if (foundLength < 0) {
            foundLength = -foundLength - 1
            notFound = true
        }
        if (notFound && foundLength >= maxLength)
            ++maxLength
        if (minValuesWhereIndexAsLength[foundLength] > i) {
            minValuesWhereIndexAsLength[foundLength] = i
            minValueIndices[foundLength] = index
            parents[index] = if (foundLength == 0) -1 else minValueIndices[foundLength - 1]
        }
    }

    val example = LinkedList<Int>()
    var index = minValueIndices[maxLength - 1]
    while (index != -1) {
        example.addFirst(index)
        index = parents[index]
    }

    val results = LinkedList<Int>()
    index = 0
    firsts.indices.forEach {
        if (index < example.size && it == example[index]) {
            ++index
        } else {
            results.add(firsts[it])
        }
    }

    println(n - maxLength)
    println(results.joinToString("\n"))
}
