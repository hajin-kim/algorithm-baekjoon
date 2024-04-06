class IndexNode(
    val index: Int,
    val prev: IndexNode?,
)

fun main(args: Array<String>) {
    val string1 = readln()
    val string2 = readln()

    val dp = IntArray(string2.length)
    val parents = Array<IndexNode?>(string2.length) { null }

    string1.forEach { c1 ->
        var previousMax = 0
        var previousMaxIndex = -1
        string2.forEachIndexed { index, c2 ->
            if (c1 == c2 && dp[index] <= previousMax) {
                dp[index] = previousMax + 1
                if (previousMaxIndex != -1)
                    parents[index] = IndexNode(index, parents[previousMaxIndex])
                else
                    parents[index] = IndexNode(index, null)
            } else if (dp[index] > previousMax) {
                previousMax = dp[index]
                previousMaxIndex = index
            }
        }
    }

    val (resultIndex, result) = dp.withIndex().maxBy { it.value }

    if (result == 0)
        println(0)
    else {
        val builder = StringBuilder()
        var node = parents[resultIndex]
        while (node != null) {
            builder.append(string2[node.index])
            node = node.prev
        }
        builder.reverse()
        val example = builder.toString()

        println(result)
        println(example)
    }
}
