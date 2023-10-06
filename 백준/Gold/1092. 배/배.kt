fun main() {
    val n = readln().toInt()
    val cranes = readln().split(" ").map { it.toInt() }.sorted().toIntArray()
    val m = readln().toInt()
    val boxes = readln().split(" ").map { it.toInt() }

    val queues = IntArray(n)

    boxes.forEach { box ->
        if (box > cranes.last()) {
            println(-1)
            return
        }

        cranes.indexOfFirst { crane -> box <= crane }
            .let { craneIndex -> ++queues[craneIndex] }
    }

    val currentQueuePointers = IntArray(n) { it }
    var count = 0
    var remainingBoxes = m
    while (remainingBoxes > 0) {
        cranes.indices.forEach {
            while (currentQueuePointers[it] >= 0 && queues[currentQueuePointers[it]] <= 0) {
                --currentQueuePointers[it]
            }

            if (currentQueuePointers[it] < 0)
                return@forEach

            --queues[currentQueuePointers[it]]
            --remainingBoxes
        }

        ++count
    }

    println(count)
}