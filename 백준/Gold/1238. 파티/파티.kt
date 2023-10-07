const val UNREACHABLE = 100000

fun main() {
    val (n, m, x) = readln().split(" ").map { it.toInt() }
    val edges = Array(m) { readln().split(" ").map { it.toInt() } }

    val graph = Array(n + 1) { y -> IntArray(n + 1) { x -> if (y == x) 0 else UNREACHABLE } }
    edges.forEach { (a, b, w) ->
        graph[a][b] = w
    }

    for (k in 1..n) {
        for (i in 1..n) {
            for (j in 1..n) {
                graph[i][j] = minOf(graph[i][j], graph[i][k] + graph[k][j])
            }
        }
    }

    val result = (1..n).maxOf {
        graph[it][x] + graph[x][it]
    }

    println(result)
}