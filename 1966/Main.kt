import java.util.*

fun main(args: Array<String>) {
    repeat(readln().toInt()) {
        val (n, index) = readln().split(" ").map { it.toInt() }
        val documents = readln()
            .split(" ")
            .mapIndexed { i, s -> s.toInt() to i }

        val queue: Queue<Pair<Int, Int>> = LinkedList()
        queue.addAll(documents)

        var result = 0
        while (true) {
            val (priority, i) = queue.poll()
            if (queue.none { it.first > priority }) {
                if (i == index) {
                    result = n - queue.size
                    break
                }
            } else {
                queue.add(priority to i)
            }
        }

        println(result)
    }
}