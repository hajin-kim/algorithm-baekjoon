fun solve(string: String): Boolean {
    var parenthesis = 0
    for (char in string) {
        when (char) {
            '(' -> ++parenthesis
            ')' -> {
                --parenthesis
                if (parenthesis < 0)
                    return false
            }
        }
    }
    return parenthesis == 0
}

fun main() {
    val t = readln().toInt()
    repeat(t) {
        val string = readln()
        
        val result = solve(string)

        println(if (result) "YES" else "NO")
    }
}