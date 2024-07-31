fun main() {
    val t = readln().toInt()
    repeat(t) {
        val words = readln().split(" ")
        val nonFoxHowlSet = mutableSetOf<String>()
        while (true) {
            val records = readln().split(" ")
            if (records[1] == "does")
                break
            nonFoxHowlSet += records[2]
        }
        val result = solve(words, nonFoxHowlSet)

        println(result)
    }
}

fun solve(words: List<String>, nonFoxHowlSet: Set<String>): String =
    words.filter { it !in nonFoxHowlSet }.joinToString(" ")
