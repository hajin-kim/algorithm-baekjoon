fun main() {
    val (r, c) = readln().split(" ").map { it.toInt() }
    val available = Array(r) { readln().map { it == '.' }.toBooleanArray() }

    fun dfs(y: Int, x: Int): Boolean {
        if (x == c)
            return true
        if (y < 0 || y >= r || !available[y][x])
            return false

        available[y][x] = false

        return (-1..1).any { dfs(y + it, x + 1) }
    }

    val result = (0 until r).count { dfs(it, 0) }
    print(result)
}