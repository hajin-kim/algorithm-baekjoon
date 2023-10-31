fun main() {
    val n = readln().toInt()
    val rooms = List(n) {
        readln().split(" ").map { it.toInt() }.let { it[0] to it[1] }
    }

    var activeRooms = 0
    var max = 0

    rooms
        .flatMap { listOf(it.first to true, it.second to false) }
        .sortedWith(compareBy<Pair<Int, Boolean>> { it.first }.thenBy { it.second })
        .forEach { (_, isStart) ->
            if (isStart) {
                ++activeRooms
                max = maxOf(max, activeRooms)
            } else
                --activeRooms
        }

    println(max)
}