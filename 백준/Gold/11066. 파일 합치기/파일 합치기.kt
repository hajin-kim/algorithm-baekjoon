fun main() {
    val t = readln().toInt()

    repeat(t) {
        val k = readln().toInt()
        val books = readln().split(" ").map { it.toInt() }.toIntArray()

        val prefixSum = books
            .scan(0) { acc, i -> acc + i }
            .toIntArray()

        fun getLength(from: Int, until: Int): Int {
            return prefixSum[until] - prefixSum[from]
        }

        val cache = Array(k + 1) { IntArray(k + 1) { -1 } }
        fun dp(from: Int, until: Int): Int {
            if (until == from + 1)
                return 0

            if (cache[from][until] != -1)
                return cache[from][until]

            val result = (from + 1..until - 1)
                .minOf {
                    dp(from, it) + dp(it, until)
                }
                .plus(getLength(from, until))
            cache[from][until] = result

            return result
        }

        val result = dp(0, k)
        println(result)
    }
}