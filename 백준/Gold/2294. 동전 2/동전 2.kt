fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }

    val coins = List(n) { readln().toInt() }

    val cache = IntArray(k + 1) { Int.MAX_VALUE }
    cache[0] = 0

    (1..k).forEach { now ->
        coins.forEach { coin ->
            val prevIndex = now - coin
            if (prevIndex >= 0 && cache[prevIndex] != Int.MAX_VALUE) {
                cache[now] = minOf(cache[prevIndex] + 1, cache[now])
            }
        }
    }

    val result = if (cache[k] == Int.MAX_VALUE) -1 else cache[k]
    println(result)
}
