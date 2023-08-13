fun main(args: Array<String>) {
    val (a, initialB) = readln().split(" ").map { it.toInt() }

    var result = 0
    var b = initialB

    while (a < b) {
        if (b % 10 == 1) {
            b /= 10
            ++result
        } else if (b % 2 == 0) {
            b /= 2
            ++result
        } else {
            println(-1)
            return
        }
    }

    if (a != b) {
        println(-1)
        return
    }
    println(result + 1)
}
