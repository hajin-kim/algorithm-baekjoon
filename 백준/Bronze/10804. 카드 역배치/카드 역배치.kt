fun main() {
    val cards = IntArray(21) { it }

    repeat(10) {
        val (a, b) = readln().split(" ").map { it.toInt() }

        cards.reverse(a, b + 1)
    }

    println(cards.drop(1).joinToString(" "))
}