fun simulateInfection(map: Array<CharArray>, chosen: MutableList<Pair<Int, Int>>): Int {
    val n = map.size
    val m = map[0].size

    val visited = Array(n) { BooleanArray(m) }

    fun dfs(y: Int, x: Int): Int {
        if (y !in 0 until n || x !in 0 until m)
            return 0

        if (visited[y][x] || map[y][x] == '1') {
            return 0
        }

        visited[y][x] = true

        return (if (map[y][x] == '0') 1 else 0) + listOf(
            y - 1 to x,
            y + 1 to x,
            y to x - 1,
            y to x + 1
        ).sumOf { (ny, nx) ->
            dfs(ny, nx)
        }
    }


    chosen.forEach { (y, x) ->
        map[y][x] = '1'
    }

    var result = 0
    (0 until n).forEach { y ->
        (0 until m).forEach { x ->
            if (!visited[y][x] && map[y][x] == '2') {
                result += dfs(y, x)
            }
        }
    }

    chosen.forEach { (y, x) ->
        map[y][x] = '0'
    }

    return result
}


fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val map = Array(n) { readln().split(" ").map { it.first() }.toCharArray() }

    val nSafeZones = map.sumOf { row ->
        row.count { it == '0' }
    }

    var result = 0
    fun permutation(y: Int, x: Int, chosen: MutableList<Pair<Int, Int>>) {
        if (chosen.size == 3) {
            val simulationResult = nSafeZones - simulateInfection(map, chosen) - 3
            result = maxOf(result, simulationResult)
            return
        }

        if (y == n) {
            return
        }

        if (x == m) {
            permutation(y + 1, 0, chosen)
            return
        }

        permutation(y, x + 1, chosen)
        if (map[y][x] == '0') {
            chosen.add(y to x)
            permutation(y, x + 1, chosen)
            chosen.removeLast()
        }
    }

    permutation(0, 0, mutableListOf())

    println(result)
}