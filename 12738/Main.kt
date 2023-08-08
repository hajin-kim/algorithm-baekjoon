import kotlin.math.min

fun main(args: Array<String>) {
    val n = readln().toInt()
    val array = readln().split(" ").map { it.toInt() }.toIntArray()

    val minValuesWhereIndexAsLength = IntArray(n) { Int.MAX_VALUE }

    var maxLength = 0
    array.forEachIndexed { index, i ->
        val indexOrInvertedInsertionPoint = minValuesWhereIndexAsLength.binarySearch(i)
        if (indexOrInvertedInsertionPoint < 0) {
            val insertionPoint = -indexOrInvertedInsertionPoint - 1
            if (insertionPoint >= maxLength) {
                ++maxLength
                minValuesWhereIndexAsLength[insertionPoint] = i
            } else {
                minValuesWhereIndexAsLength[insertionPoint] = min(minValuesWhereIndexAsLength[insertionPoint], i)
            }
        } else {
            minValuesWhereIndexAsLength[indexOrInvertedInsertionPoint] =
                min(minValuesWhereIndexAsLength[indexOrInvertedInsertionPoint], i)
        }
    }

    println(maxLength)
}
