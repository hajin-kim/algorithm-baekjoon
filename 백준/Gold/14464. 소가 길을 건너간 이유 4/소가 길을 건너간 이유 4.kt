import java.util.PriorityQueue

enum class Type {
    COW,
    CHICKEN,
}

data class Animal(
    val start: Int,
    val end: Int,
    val type: Type,
)


fun solve(chickens: List<Int>, cows: List<Pair<Int, Int>>): Int {
    val priorityQueue = PriorityQueue(compareBy<Animal> { it.start }.thenBy { it.type })

    for (time in chickens) {
        priorityQueue.offer(Animal(time, -1, Type.CHICKEN))
    }
    for ((begin, end) in cows) {
        priorityQueue.offer(Animal(begin, end, Type.COW))
    }

    var result = 0
    val waitingCows = PriorityQueue(compareBy<Animal> { it.end })

    while (priorityQueue.isNotEmpty()) {
        val animal = priorityQueue.poll()

        when (animal.type) {
            Type.COW -> waitingCows.add(animal)
            Type.CHICKEN -> {
                while (waitingCows.isNotEmpty() && waitingCows.peek().end < animal.start) {
                    waitingCows.poll()
                }
                if (waitingCows.isNotEmpty()) {
                    waitingCows.poll()
                    ++result
                }
            }
        }
    }

    return result
}

fun main() {
    val (c, n) = readln().split(" ").map { it.toInt() }

    val chickens = List(c) {
        readln().toInt()
    }
    val cows = List(n) {
        readln().split(" ").map { it.toInt() }.let { it[0] to it[1] }
    }

    val result = solve(chickens, cows)

    println(result)
}