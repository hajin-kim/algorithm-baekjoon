import kotlin.math.min

fun main(args: Array<String>) {
    val n = readln().toInt()
    val costsArray = (1..n)
        .map {
            readln()
                .split(" ")
                .map { it.toInt() }
                .toIntArray()
        }
        .toTypedArray()

    // dp[i][j] = 첫 번째 집을 i색, 가장 최근 집을 j색으로 칠했을 때 최소 비용
    var dp = Array(3) { IntArray(3) { 1000001 } }

    (0..2).forEach { i ->
        dp[i][i] = costsArray[0][i]
    }

    costsArray.drop(1).dropLast(1).forEach { costs ->
        val nextDp = Array(3) { IntArray(3) { 1000001 } }

        (0..2).forEach { first ->
            (0..2).forEach { new ->
                (0..2).forEach { prev ->
                    if (prev != new)
                        nextDp[first][new] = min(dp[first][prev] + costs[new], nextDp[first][new])
                }
            }
        }

        dp = nextDp
    }

    val lastCosts = costsArray.last()

    dp[0][0] += min(lastCosts[1], lastCosts[2])
    dp[1][1] += min(lastCosts[0], lastCosts[2])
    dp[2][2] += min(lastCosts[0], lastCosts[1])

    dp[0][1] += lastCosts[2]
    dp[0][2] += lastCosts[1]
    dp[1][0] += lastCosts[2]
    dp[1][2] += lastCosts[0]
    dp[2][0] += lastCosts[1]
    dp[2][1] += lastCosts[0]

    val result = dp.minOf { it.min() }
    println(result)
}
