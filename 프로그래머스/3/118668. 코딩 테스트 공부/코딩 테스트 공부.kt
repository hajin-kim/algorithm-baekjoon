class Solution {
    private val MAX = 1000000000

    fun solution(alp: Int, cop: Int, problems: Array<IntArray>): Int {
        val maxAlp = problems.maxOf { it[0] }
        val maxCop = problems.maxOf { it[1] }

        val dp = Array(maxAlp + 1) { IntArray(maxCop + 1) { MAX } }

        for (a in 0..minOf(alp, maxAlp)) {
            for (c in 0..minOf(cop, maxCop)) {
                dp[a][c] = 0
            }
        }

        for (a in 0..maxAlp) {
            for (c in 0..maxCop) {
                if (a < maxAlp)
                    dp[a + 1][c] = minOf(dp[a][c] + 1, dp[a + 1][c])
                if (c < maxCop)
                    dp[a][c + 1] = minOf(dp[a][c] + 1, dp[a][c + 1])

                for ((alpReq, copReq, alpRwd, copRwd, cost) in problems) {
                    if (a < alpReq || c < copReq)
                        continue
                    val nextAlp = (a + alpRwd).coerceAtMost(maxAlp)
                    val nextCop = (c + copRwd).coerceAtMost(maxCop)
                    dp[nextAlp][nextCop] = minOf(dp[a][c] + cost, dp[nextAlp][nextCop])
                }
            }
        }

        return dp[maxAlp][maxCop]
    }
}
