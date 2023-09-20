fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val values = IntArray(n) { readln().toInt() }.sortedArray()

    val dp = IntArray(k + 1)
    for (value in values) {
        if (value > k)
            break

        ++dp[value]
        for (i in (value + 1)..k) {
            dp[i] += dp[i - value]
        }
    }

    println(dp[k])
}