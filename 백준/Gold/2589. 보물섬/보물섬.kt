import java.util.LinkedList
import java.util.Queue

const val LAND = 'L'

fun bfs(height: Int, width: Int, maps: Array<CharArray>, start: Pair<Int, Int>): Int {
    fun isInRange(h: Int, w: Int): Boolean =
        h in 0 until height && w in 0 until width

    if (maps[start.first][start.second] != LAND)
        return 0

    val discovered = Array(height) { BooleanArray(width) }
    val queue: Queue<Pair<Int, Int>> = LinkedList()


    discovered[start.first][start.second] = true
    queue.add(start)

    var minDistance = -1
    while (queue.isNotEmpty()) {
        minDistance++
        repeat(queue.size) {
            val (h, w) = queue.poll()

            val neighbors = listOf(
                h to w + 1,
                h to w - 1,
                h + 1 to w,
                h - 1 to w,
            )
            for (neighbor in neighbors) {
                val (nh, nw) = neighbor

                if (!isInRange(nh, nw))
                    continue
                if (maps[nh][nw] != LAND)
                    continue

                if (discovered[nh][nw])
                    continue
                discovered[nh][nw] = true

                queue.add(neighbor)
            }
        }
    }

    return minDistance
}

fun solve(height: Int, width: Int, maps: Array<CharArray>): Int {
    return (0 until height).maxOf { h ->
        (0 until width).maxOf { w ->
            bfs(height, width, maps, h to w)
        }
    }
}

fun main() {
    val (height, width) = readln().split(" ").map { it.toInt() }
    val maps = Array(height) { readln().toCharArray() }

    val result = solve(height, width, maps)

    println(result)
}