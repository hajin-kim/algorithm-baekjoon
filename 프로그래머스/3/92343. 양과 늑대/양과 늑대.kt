class Solution {
    fun solution(info: IntArray, edges: Array<IntArray>): Int {
        val edgeMap = let {
            val firstToSecond = edges.groupBy { it[0] }.mapValues { (_, v) -> v.map { it[1] } }
            val secondToFirst = edges.groupBy { it[1] }.mapValues { (_, v) -> v.map { it[0] } }

            firstToSecond + secondToFirst
        }

        val visited = BooleanArray(info.size)
        var answer = 0

        fun dfs(now: Int, sheep: Int, wolf: Int) {
            if (visited[now])
                return

            val nextSheep = sheep + (1 - info[now])
            val nextWolf = wolf + info[now]
            if (nextSheep == nextWolf)
                return

            visited[now] = true


            answer = maxOf(answer, nextSheep)

            for ((start, end) in edges) {
                if (visited[start] && !visited[end])
                    dfs(end, nextSheep, nextWolf)
            }

            visited[now] = false
        }
        dfs(0, 0, 0)

        return answer
    }
}
