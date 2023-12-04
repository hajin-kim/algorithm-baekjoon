const val MODULO = 1000000

fun solve(a: String): Int {
    if (a.isEmpty())
        return 0
    if (a.startsWith('0'))
        return 0

    val n = a.length
    val cache = IntArray(n + 1)
    cache[0] = 1
    cache[1] = 1

    for (i in 2..n) {
        if (a[i - 1] != '0')
            cache[i] = cache[i - 1]
        if (a.slice(i - 2..i - 1) in "10".."26") {
            cache[i] += cache[i - 2]
            cache[i] %= MODULO
        }

        if (cache[i] == 0)
            return 0
    }

    return cache[n]
}

fun main() {
    val password = readln()

    val result = solve(password)

    println(result)
}