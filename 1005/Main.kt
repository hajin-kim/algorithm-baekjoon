fun main(args: Array<String>) {
    val t = readln().toInt()
    repeat(t) {
        val (n, k) = readln().split(" ").map { it.toInt() }
        val times = readln()
            .split(" ")
            .map { it.toInt() }
            .let { listOf(0) + it }
            .toIntArray()
        val reversedEdges = (1..k)
            .map {
                readln().split(" ").let { it[1].toInt() to it[0].toInt() }
            }
            .groupBy({ it.first }, { it.second })
        val w = readln().toInt()


        val maxTimes = IntArray(1000 + 1) { -1 }

        fun topologyDp(start: Int): Int {
            if (maxTimes[start] != -1)
                return maxTimes[start]

            val neighbors = reversedEdges[start]
            if (neighbors == null) {
                maxTimes[start] = times[start]
                return times[start]
            }

            val maxTime = neighbors.maxOf { neighbor ->
                topologyDp(neighbor)
            } + times[start]

            maxTimes[start] = maxTime
            return maxTime
        }


        val result = topologyDp(w)

        println(result)
    }
}
