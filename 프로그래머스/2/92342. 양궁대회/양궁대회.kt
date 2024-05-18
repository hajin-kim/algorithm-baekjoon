class Solution {
    fun solution(n: Int, info: IntArray): IntArray {
        var maxAnswer = intArrayOf(-1)
        var maxRelativeScore = 0

        fun IntArray.isGreaterThan(other: IntArray, i: Int = 10): Boolean {
            return if (this[i] > other[i])
                true
            else if (this[i] == other[i])
                isGreaterThan(other, i - 1)
            else false
        }

        fun mutablePermute(target: Int, relativeScore: Int, remainingN: Int, answer: IntArray) {
            val i = 10 - target
            if (target == 0) {
                if (relativeScore <= 0 || relativeScore < maxRelativeScore)
                    return

                answer[i] = remainingN
                if (relativeScore > maxRelativeScore || answer.isGreaterThan(maxAnswer)
                ) {
                    maxRelativeScore = relativeScore
                    maxAnswer = answer.copyOf()
                }
                answer[i] = 0
                return
            }

            val nextTarget = target - 1
            val required = info[i] + 1
            val scoreLoss = if (required == 1) relativeScore else relativeScore - target

            mutablePermute(nextTarget, scoreLoss, remainingN, answer)
            if (remainingN >= required) {
                answer[i] = required
                mutablePermute(nextTarget, relativeScore + target, remainingN - required, answer)
                answer[i] = 0
            }
        }

        mutablePermute(10, 0, n, IntArray(11))

        return maxAnswer
    }
}
