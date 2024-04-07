class Solution {
    fun solution(coin: Int, cards: IntArray): Int {
        val n = cards.size
        val target = n + 1

        val isInInitialHand = BooleanArray(n + 1)
        val candidates = mutableSetOf<Int>()

        @Suppress("NAME_SHADOWING")
        var coin = coin
        var initialPairs = 0

        var next = 0

        repeat(n / 3) {
            val drawn = cards[next++]
            val opposite = target - drawn

            if (isInInitialHand[opposite]) {
                ++initialPairs
                isInInitialHand[opposite] = false
            } else
                isInInitialHand[drawn] = true
        }

        var result = 1
        while (next < n) {
            candidates += cards[next++]
            candidates += cards[next++]

            var shouldPassRound = false

            if (initialPairs > 0) {
                --initialPairs
                shouldPassRound = true
            }

            if (!shouldPassRound && coin >= 1) {
                for (candidate in candidates) {
                    val opposite = target - candidate
                    if (isInInitialHand[opposite]) {
                        isInInitialHand[opposite] = false
                        candidates.remove(candidate)
                        --coin
                        shouldPassRound = true
                        break
                    }
                }
            }

            if (!shouldPassRound && coin >= 2) {
                for (candidate in candidates) {
                    val opposite = target - candidate
                    if (candidates.contains(opposite)) {
                        candidates.remove(candidate)
                        candidates.remove(opposite)
                        coin -= 2
                        shouldPassRound = true
                        break
                    }
                }
            }

            if (shouldPassRound) {
                ++result
            } else
                break
        }

        return result
    }
}
