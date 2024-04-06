fun main() {
    val (n, l) = readln().split(" ").map { it.toInt() }
    val heights = Array(n) {
        readln()
            .split(" ")
            .map { it.toInt() }
            .toTypedArray()
    }

    val result = listOf<(Int, Int) -> Int>(
        { fixed, current -> heights[fixed][current] },
        { fixed, current -> heights[current][fixed] },
    ).sumOf { selector ->
        (0 until n).map { fixed ->
            var ascendingCount = 1
            var descendingCount = l

            (1 until n).forEach { current ->
                val currentHeight = selector(fixed, current)
                val prev = selector(fixed, current - 1)

                when (currentHeight) {
                    prev -> {
                        if (descendingCount < l) descendingCount++
                        else ascendingCount++
                    }

                    prev + 1 -> {
                        if (ascendingCount < l || descendingCount < l)
                            return@map 0
                        ascendingCount = 1
                    }

                    prev - 1 -> {
                        if (descendingCount < l)
                            return@map 0
                        ascendingCount = 0
                        descendingCount = 1
                    }

                    else -> return@map 0
                }
            }

            if (descendingCount < l) 0
            else 1
        }.sum()
    }

    println(result)
}