fun main(args: Array<String>) {
    val string1 = readln()
    val string2 = readln()

    val dp = IntArray(string2.length)

    string1.forEach { c1 ->
        var previousMax = 0
        string2.forEachIndexed { index, c2 ->
            if (c1 == c2 && dp[index] <= previousMax) {
                dp[index] = previousMax + 1
            } else if (dp[index] > previousMax) {
                previousMax = dp[index]
            }
        }
    }

    val result = dp.max()
    println(result)
}