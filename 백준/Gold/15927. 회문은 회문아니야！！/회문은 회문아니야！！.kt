fun main() {
    val string = readln()
    val result = solve(string)
    println(result ?: -1)
}

fun String.isRepeated(): Boolean {
    var isRepeated = true
    for (char in this) {
        if (char != this[0]) {
            isRepeated = false
            break
        }
    }
    return isRepeated
}

fun String.isPalindrome(): Boolean {
    var start = 0
    var end = lastIndex

    while (start < end) {
        if (this[start] != this[end])
            return false
        ++start
        --end
    }

    return true
}

fun solve(string: String): Int? {
    if (string.isRepeated()) return null

    return if (string.isPalindrome())
        string.length - 1 else string.length
}
