class Solution {
    fun solution(info: IntArray, edges: Array<IntArray>): Int {
        val visited = BooleanArray(info.size)
        var answer = 0

        fun dfs(now: Int, sheepBefore: Int, wolfBefore: Int) {
            if (visited[now])
                return

            val sheep = sheepBefore + (1 - info[now])
            val wolf = wolfBefore + info[now]
            if (sheep == wolf)
                return

            visited[now] = true

            answer = maxOf(answer, sheep)

            for ((start, end) in edges) {
                if (visited[start] && !visited[end])
                    dfs(end, sheep, wolf)
            }

            visited[now] = false
        }

        dfs(0, 0, 0)

        return answer
    }
}
