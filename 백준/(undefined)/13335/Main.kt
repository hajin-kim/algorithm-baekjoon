import java.util.*

fun main(args: Array<String>) {
    val (nTrucks, bridgeLength, maxWeight) = readln().split(" ").map { it.toInt() }
    val readyTrucks: Queue<Int> = readln().split(" ").map { it.toInt() }.toCollection(LinkedList())
    val bridge: Queue<Int> = IntArray(bridgeLength).apply { fill(0) }.toCollection(LinkedList())

    var time = 0
    var currentWeight = 0
    while (readyTrucks.isNotEmpty()) {
        currentWeight -= bridge.poll()

        if (currentWeight + readyTrucks.peek() <= maxWeight) {
            val truck = readyTrucks.poll()
            bridge.add(truck)
            currentWeight += truck
        } else {
            bridge.add(0)
        }

        ++time
    }

    println(time + bridgeLength)
}
