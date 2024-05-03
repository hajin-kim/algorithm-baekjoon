// 18:30 ~ 18:45
class Solution {
    fun solution(queue1: IntArray, queue2: IntArray): Int {
        val n = queue1.size
        var sum1 = queue1.sum().toLong()
        var sum2 = queue2.sum().toLong()
        var i1 = 0
        var i2 = 0

        var count = 0
        while (true) {
            if (sum1 == sum2)
                return count

            if (sum1 < sum2) {
                if (i2 == 2 * n)
                    return -1

                if (i2 < n) {
                    sum1 += queue2[i2]
                    sum2 -= queue2[i2]
                } else {
                    sum1 += queue1[i2 - n]
                    sum2 -= queue1[i2 - n]
                }
                ++i2
            } else {
                if (i1 == 2 * n)
                    return -1

                if (i1 < n) {
                    sum2 += queue1[i1]
                    sum1 -= queue1[i1]
                } else {
                    sum2 += queue2[i1 - n]
                    sum1 -= queue2[i1 - n]
                }
                ++i1
            }

            ++count
        }

        return -1
    }
}
