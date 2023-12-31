fun solve(students: Array<Pair<String, List<Int>>>): List<String> {
    val comparator = compareByDescending<Pair<String, List<Int>>> { it.second[0] }
        .thenBy { it.second[1] }
        .thenByDescending { it.second[2] }
        .thenBy { it.first }

    return students
        .sortedWith(comparator)
        .map { it.first }
}

fun main() {
    val n = readln().toInt()
    val students = Array(n) {
        readln().split(" ")
            .let { it[0] to it.drop(1).map { score -> score.toInt() } }
    }

    val result = solve(students)

    println(result.joinToString("\n"))
}