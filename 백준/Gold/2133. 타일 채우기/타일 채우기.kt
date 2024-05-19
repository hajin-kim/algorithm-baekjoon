fun main() {
    val n = readln().toInt()

    val result = solve(n)

    println(result)
}

const val UNDEFINED = -1

// 2 -> 3
// 4 -> 11
// 6 -> 41
// 8 -> 153
fun solve(n: Int): Int {
    if (n % 2 == 1)
        return 0

    val scaledN = n / 2

    val combination = IntArray(scaledN) { UNDEFINED }

    // n == 2
    combination[0] = 3

    fun dp(i: Int): Int {
        if (combination[i] != UNDEFINED)
            return combination[i]

        val separateFirstTwoColumns = 3 * dp(i - 1)
        combination[i] = separateFirstTwoColumns

        for (j in 0 until (i - 1)) {
            val concatFirstColumns = 2 * dp(j)
            combination[i] += concatFirstColumns
        }
        
        val concatAllColumns = 2
        combination[i] += concatAllColumns

        return combination[i]
    }

    return dp(scaledN - 1)
}
