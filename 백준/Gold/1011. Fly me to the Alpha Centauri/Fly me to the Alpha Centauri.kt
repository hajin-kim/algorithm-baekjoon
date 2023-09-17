fun main() {
    val t = readln().toInt()
    repeat(t) {
        val (x, y) = readln().split(" ").map { it.toInt() }
        var diff = y - x

        var result: Int
        var count = 4

        when (diff) {
            1 -> {
                result = 1
            }

            2 -> {
                result = 2
            }

            else -> {
                result = 2
                diff -= 2
                while (diff > 0) {
                    diff -= count / 2
                    count++
                    result++
                }
            }
        }

        println(result)
    }
}