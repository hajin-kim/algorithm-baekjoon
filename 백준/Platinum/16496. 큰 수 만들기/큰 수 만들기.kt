fun main() {
    val n = readln().toInt()
    val list = readln().split(" ")

    val result = list.sortedWith(
        Comparator<String> { o1, o2 ->
            o1.plus(o2).compareTo(o2.plus(o1))
        }.reversed()
    )
        .joinToString("")

    println(if (result.startsWith('0')) "0" else result)
}