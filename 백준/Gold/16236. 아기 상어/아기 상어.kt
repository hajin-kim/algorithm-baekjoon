import java.util.*

fun main() {
    val n = readln().toInt()
    val map = Array(n) {
        readln().split(" ").map { it.toInt() }.toIntArray()
    }

    var position: Pair<Int, Int> = 0 to 0
    for (y in 0 until n)
        for (x in 0 until n)
            if (map[y][x] == 9) {
                map[y][x] = 0
                position = y to x
            }

    fun isInRange(y: Int, x: Int) =
        y in 0 until n && x in 0 until n

    fun bfs(currentWeight: Int, start: Pair<Int, Int>): Triple<Int, Int, Int>? {
        val discovered = Array(n) { BooleanArray(n) }
        var queue: Queue<Pair<Int, Int>> = LinkedList()

        discovered[start.first][start.second] = true
        queue.add(start)

        var time = 0
        val resultCandidates = PriorityQueue(
            compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second }
        )

        while (queue.isNotEmpty()) {
            ++time

            val toVisit: Queue<Pair<Int, Int>> = LinkedList()

            while (queue.isNotEmpty()) {
                val (y, x) = queue.poll()

                val neighbors = listOf(
                    y - 1 to x,
                    y to x - 1,
                    y to x + 1,
                    y + 1 to x,
                )

                neighbors.forEach { neighbor ->
                    val (ny, nx) = neighbor
                    if (!isInRange(ny, nx))
                        return@forEach

                    if (map[ny][nx] > currentWeight)
                        return@forEach

                    if (discovered[ny][nx])
                        return@forEach
                    discovered[ny][nx] = true

                    if (map[ny][nx] in 1 until currentWeight)
                        resultCandidates.add(neighbor)
                    else
                        toVisit.add(neighbor)
                }
            }

            if (resultCandidates.isNotEmpty()) {
                val (ny, nx) = resultCandidates.poll()
                map[ny][nx] = 0
                return Triple(ny, nx, time)
            }

            queue = toVisit
        }

        return null
    }

    var nEaten = 0
    var sharkWeight = 2
    var time = 0
    while (true) {
        val bfsResult = bfs(sharkWeight, position)
            ?: break

        val (ny, nx, elapsedTime) = bfsResult
        position = ny to nx
        time += elapsedTime

        ++nEaten
        if (nEaten == sharkWeight) {
            nEaten = 0
            ++sharkWeight
        }
    }

    println(time)
}