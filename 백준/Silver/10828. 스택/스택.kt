class Stack<T> {
    private val list = mutableListOf<T>()

    fun pop(): T {
        return list.removeLast()
    }

    fun push(t: T) {
        list.add(t)
    }

    fun isEmpty(): Boolean {
        return list.isEmpty()
    }

    fun top(): T {
        return list.last()
    }

    fun size(): Int {
        return list.size
    }
}

fun solve(commands: List<Pair<String, Int?>>): MutableList<Int> {
    val stack = Stack<Int>()

    val results = mutableListOf<Int>()

    commands.forEach { (command, arg) ->
        when (command) {
            "push" -> stack.push(arg!!)
            "pop" -> results.add(if (stack.isEmpty()) -1 else stack.pop())
            "size" -> results.add(stack.size())
            "empty" -> results.add(if (stack.isEmpty()) 1 else 0)
            "top" -> results.add(if (stack.isEmpty()) -1 else stack.top())
        }
    }

    return results
}

fun main() {
    val n = readln().toInt()
    val commands = List(n) {
        readln().split(" ").let {
            if (it[0] == "push")
                it[0] to it[1].toInt()
            else
                it[0] to null
        }
    }

    val result = solve(commands)

    println(result.joinToString("\n"))
}