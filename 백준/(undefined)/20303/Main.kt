import java.util.*

fun main() {
    val (n, m, k) = readln().split(" ").map { it.toInt() }
    val cs = (listOf(0) + readln().split(" ").map { it.toInt() }).toIntArray()
    val relationships = (1..m)
        .flatMap {
            readln().split(" ").map { it.toInt() }.let { listOf(it[0] to it[1], it[1] to it[0]) }
        }
        .groupBy({ it.first }, { it.second })

    val visited = BooleanArray(n + 1)

    fun dfs(start: Int): Pair<Int, Int> {
        var nCandies = 0
        var nChildren = 0
        val stack = Stack<Int>().apply { push(start) }

        while (stack.isNotEmpty()) {
            val node = stack.pop()

            if (visited[node])
                continue

            nCandies += cs[node]
            ++nChildren

            visited[node] = true
            stack.addAll(relationships[node] ?: emptyList())
        }

        return nChildren to nCandies
    }

    val items = (1..n)
        .fold(mutableListOf<Pair<Int, Int>>()) { acc, start ->
            if (!visited[start])
                acc.add(dfs(start))

            acc
        }

    val dp = IntArray(k) { -1 }.apply { this[0] = 0 }
    items.forEach { (nChildren, nCandies) ->
        ((k - nChildren - 1) downTo 0)
            .forEach {
                if (dp[it] != -1)
                    dp[it + nChildren] = maxOf(dp[it + nChildren], dp[it] + nCandies)
            }
    }

    println(dp.max())
}