// 18:15 ~ 18:28 (약간 인터럽트가 있었음)
class Solution {
    fun solution(survey: Array<String>, choices: IntArray): String {
        val n = survey.size

        val scoreMap = mutableMapOf<Char, Int>()

        repeat(n) {
            val (negative, positive) = survey[it].toCharArray()
            val choice = choices[it]

            if (choice > 4) {
                scoreMap[positive] = (scoreMap[positive] ?: 0) + (choice - 4)
            } else if (choice < 4) {
                scoreMap[negative] = (scoreMap[negative] ?: 0) + (4 - choice)
            }
        }

        return buildString {
            val options = listOf(
                listOf('R', 'T'),
                listOf('C', 'F'),
                listOf('J', 'M'),
                listOf('A', 'N'),
            )

            for ((first, second) in options) {
                if ((scoreMap[first] ?: 0) >= (scoreMap[second] ?: 0))
                    append(first)
                else
                    append(second)
            }
        }
    }
}
