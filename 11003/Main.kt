fun main() {
    val (n, l) = readln().split(" ").map { it.toInt() }
    val numberStrings = readln().split(" ")

    // index to value
    val deque = ArrayDeque<Pair<Int, Int>>(n)
    val resultBuilder = StringBuilder()

    numberStrings.forEachIndexed { index, aString ->
        val a = aString.toInt()
        while (deque.isNotEmpty() && deque.last().second >= a)
            deque.removeLast()
        deque.addLast(index to a)
        while (deque.first().first <= index - l)
            deque.removeFirst()
        resultBuilder.append(deque.first().second)
        resultBuilder.append(' ')
    }

    println(resultBuilder)
}
