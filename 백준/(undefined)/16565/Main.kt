fun main(args: Array<String>) {
    val n = readln().toInt()

    if (n < 4) {
        println(0)
        return
    }

    val modBy = 10007

    val combinations = Array(53) { IntArray(53) }

    fun combination(n: Int, r: Int): Int {
        if (n == r || r == 0)
            return 1
        if (combinations[n][r] != 0)
            return combinations[n][r]

        val combination = (combination(n - 1, r - 1) + combination(n - 1, r)) % modBy
        combinations[n][r] = combination
        return combination
    }

    val chooseFourCards = n / 4

    val resultOrNegative = (1..chooseFourCards)
        .sumOf {
            val chooseOthers = n - it * 4
            val sign = if (it % 2 == 0) -1 else 1
            sign * combination(13, it) * combination(52 - it * 4, chooseOthers)
        } % modBy

    println((resultOrNegative + modBy) % modBy)
}
