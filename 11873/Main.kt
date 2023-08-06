import java.util.*

fun main(args: Array<String>) {
    while (true) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        if (n == 0 && m == 0) break

        testcase(n, m)
    }
}

fun testcase(n: Int, m: Int) {
    val matrix = (1..n).map { readln().split(" ") }

    val columnWiseHeightsArray = Array(m + 1) { Array(n + 1) { 0 } }
    matrix.forEachIndexed { y, it ->
        it.forEachIndexed { x, b ->
            columnWiseHeightsArray[x + 1][y] = if (b == "1") columnWiseHeightsArray[x][y] + 1 else 0
        }
    }

    var result = 0
    (1..m).forEach { column ->
        val columnWiseHeights = columnWiseHeightsArray[column]

        val stack = Stack<Pair<Int, Int>>()
        columnWiseHeights.forEachIndexed { index, height ->
            var rangeStart = index
            while (stack.isNotEmpty() && stack.peek().first >= height) {
                val top = stack.pop()
                rangeStart = top.second
                val area = top.first * (index - top.second)
                result = maxOf(result, area)
            }
            stack.push(height to rangeStart)
        }
    }

    println(result)

}