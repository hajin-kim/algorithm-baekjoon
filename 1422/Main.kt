fun main() {
    val (k, n) = readln().split(" ").map { it.toInt() }
    val list = (1..k).map { readln() }

    val max = list.maxOfOrNull { it.toInt() }

    val result = list
        .plus(
            (1..n - k).map { max.toString() }
        )
        .sortedWith(
            Comparator<String> { o1, o2 ->
                o1.plus(o2).compareTo(o2.plus(o1))
            }.reversed()
        )
        .take(n)
        .joinToString("")

    println(result)
}