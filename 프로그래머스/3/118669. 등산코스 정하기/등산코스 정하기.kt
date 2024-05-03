// 8:50~9:22
import java.util.PriorityQueue

class ArrayGraph(
    private val n: Int,
    private val edgesArray: Array<MutableList<Pair<Int, Int>>>,
    private val isGate: BooleanArray,
    private val isSummit: BooleanArray,
) {
    fun dijkstra(start: Int): Int {
        val visited = BooleanArray(n)
        val intensities = IntArray(n) { UNREACHABLE }
        val priorityQueue = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })

        priorityQueue.offer(start to 0)
        intensities[start] = 0

        while (priorityQueue.isNotEmpty()) {
            val (now, nowDistance) = priorityQueue.poll()

            if (visited[now])
                continue
            visited[now] = true

            if (isGate[now])
                return nowDistance

            if (now != start && isSummit[now]) {
                continue
            }

            val neighbors = edgesArray[now]

            for ((neighbor, cost) in neighbors) {
                val newDistance = maxOf(nowDistance, cost)

                if (newDistance < intensities[neighbor]) {
                    intensities[neighbor] = newDistance
                    priorityQueue.offer(neighbor to newDistance)
                }
            }
        }

        return UNREACHABLE
    }

    companion object {
        const val UNREACHABLE = 1000000000
    }
}


class Solution {
    fun solution(n: Int, paths: Array<IntArray>, gates: IntArray, summits: IntArray): IntArray {
        val edgesArray = Array(n + 1) {
            mutableListOf<Pair<Int, Int>>()
        }
        for ((a, b, cost) in paths) {
            edgesArray[a].add(b to cost)
            edgesArray[b].add(a to cost)
        }

        val isGate = BooleanArray(n + 1)
        for (gate in gates) {
            isGate[gate] = true
        }

        summits.sort()

        val isSummit = BooleanArray(n + 1)
        for (summit in summits) {
            isSummit[summit] = true
        }

        val arrayGraph = ArrayGraph(
            n = n + 1,
            edgesArray = edgesArray,
            isGate = isGate,
            isSummit = isSummit
        )

        var minIntensity = Int.MAX_VALUE
        var minIntensitySummit = -1
        for (summit in summits) {
            val intensity = arrayGraph.dijkstra(summit)
            if (intensity < minIntensity) {
                minIntensity = intensity
                minIntensitySummit = summit
            }
        }

        return intArrayOf(minIntensitySummit, minIntensity)
    }
}
