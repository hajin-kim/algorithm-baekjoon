fun main(args: Array<String>) {
    val n = readln().toInt()
    val numbers = readln()
        .split(" ")
        .map { it.toInt() }
        .toIntArray()

    val isPalindrome = Array(n) { BooleanArray(n) }

    tailrec fun check(left: Int, right: Int) {
        if (left < 0 || right >= n)
            return
        if (numbers[left] == numbers[right]) {
            isPalindrome[left][right] = true
            check(left - 1, right + 1)
        }
    }

    (0 until n).forEach { check(it, it) }
    (1 until n).forEach { check(it - 1, it) }

    val m = readln().toInt()
    val results = (1..m).map {
        val (s, e) = readln()
            .split(" ")
            .map { it.toInt() - 1 }

        if (isPalindrome[s][e]) 1 else 0
    }
    println(results.joinToString("\n"))
}
