fun floyd(n: Int, edges: List<Triple<Int, Int, Int>>): Array<IntArray> {
    val distances = Array(n) { IntArray(n) { Int.MAX_VALUE } }
//    val next = Array(n) { IntArray(n) { -1 } }

    for (i in 0 until n) {
        distances[i][i] = 0
    }

    for ((u, v, w) in edges) {
        if (distances[u][v] > w) {
            distances[u][v] = w
//            next[u][v] = v
        }
    }

    for (transit in 0 until n) {
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (distances[i][transit] == Int.MAX_VALUE || distances[transit][j] == Int.MAX_VALUE)
                    continue

                if (distances[i][j] > distances[i][transit] + distances[transit][j]) {
                    distances[i][j] = distances[i][transit] + distances[transit][j]
//                    next[i][j] = next[i][transit]
                }
            }
        }
    }

    return distances
}

fun main() {
    val n = readln().toInt()
    val m = readln().toInt()
    val edges = List(m) {
        val (u, v, w) = readln().split(" ").map { it.toInt() }
        Triple(u, v, w)
    }

    val distances = floyd(n + 1, edges)

    val result = (1..n).joinToString("\n") { y ->
        (1..n).joinToString(" ") { x ->
            if (distances[y][x] == Int.MAX_VALUE) "0" else distances[y][x].toString()
        }
    }

    println(result)
}