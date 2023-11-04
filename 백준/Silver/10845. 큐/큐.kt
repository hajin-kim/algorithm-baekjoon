import java.util.*

class Queue<T> {
    private val linkedList = LinkedList<T>()

    fun push(x: T) {
        linkedList.addLast(x)
    }

    fun pop(): T = linkedList.removeFirst()

    fun size(): Int = linkedList.size

    fun empty(): Boolean = linkedList.isEmpty()

    fun front(): T = linkedList.first

    fun back(): T = linkedList.last
}

fun solve(commands: List<Pair<String, Int?>>): List<Int> {
    val queue = Queue<Int>()
    val result = mutableListOf<Int>()

    for ((command, arg) in commands) {
        when (command) {
            "push" -> queue.push(arg!!)
            "pop" -> result.add(
                if (queue.empty()) -1 else queue.pop()
            )

            "size" -> result.add(queue.size())
            "empty" -> result.add(if (queue.empty()) 1 else 0)
            "front" -> result.add(if (queue.empty()) -1 else queue.front())
            "back" -> result.add(if (queue.empty()) -1 else queue.back())
        }
    }

    return result
}

fun main() {
    val n = readln().toInt()
    val commands = List(n) {
        readln()
            .split(" ")
            .let { if (it.size == 1) it[0] to null else it[0] to it[1].toInt() }
    }

    val result = solve(commands)

    println(result.joinToString("\n"))
}