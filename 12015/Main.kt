import java.util.*

fun main(args: Array<String>) {
    val n = readln().toInt()
    val array = readln().split(" ").map { it.toInt() }.toIntArray()

    val minValuesWhereIndexAsLength = IntArray(n) { Int.MAX_VALUE }
    val minValueIndices = IntArray(n)
    val parents = IntArray(n) { -1 }

    var maxLength = 0
    array.forEachIndexed { index, i ->
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
        example.addFirst(array[index])
        index = parents[index]
    }

    println(maxLength)
    println(example.joinToString(" "))
}
