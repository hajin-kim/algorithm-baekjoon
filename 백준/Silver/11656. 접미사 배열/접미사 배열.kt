import java.util.LinkedList

fun solve(s: String): List<String> {
    val prefixArray = mutableListOf<String>()

    val list = s.toCollection(LinkedList())
    repeat(s.length) {
        prefixArray.add(list.joinToString(""))
        list.removeFirst()
    }

    return prefixArray.sorted()
}

fun main() {
    val s = readln()

    val result = solve(s)

    println(result.joinToString("\n"))
}