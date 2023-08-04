import java.util.*

fun main(args: Array<String>) {
    val sentence = readln()
    val bomb = readln()

    val bombIndexStack = Stack<Int>()
    val result = mutableListOf<Char>()
    sentence.forEach {
        val currentIndex = if (bombIndexStack.isEmpty()) 0 else bombIndexStack.peek()
        result.add(it)

        if (it == bomb[currentIndex] || it == bomb[0]) {
            val nextIndex =
                if (it == bomb[0]) 1
                else
                    bombIndexStack.pop() + 1

            bombIndexStack.push(nextIndex)

            if (nextIndex == bomb.length) {
                repeat(bomb.length) { result.removeLast() }
                bombIndexStack.pop()
            }
        } else
            bombIndexStack.clear()
    }

    if (result.isEmpty())
        println("FRULA")
    else
        println(result.joinToString(""))
}
