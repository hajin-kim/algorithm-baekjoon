fun main() {
    val n = readln().toInt()

    val max = (1 until n).fold(0) { acc, i ->
        acc + i
    }
    val maxExamples = (1 until n).scan(1) { acc, _ ->
        acc * 2
    }

    val min = n - 1
    val minExamples = (1..n)

    println(max)
    println(maxExamples.joinToString(" "))
    println(min)
    println(minExamples.joinToString(" "))
}