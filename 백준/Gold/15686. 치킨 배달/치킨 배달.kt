import kotlin.math.abs

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val city = Array(n) {
        readln().split(" ").map { it[0] }.toCharArray()
    }

    fun listPositionsOf(toFind: Char) = city.flatMapIndexed { y, row ->
        row
            .withIndex()
            .filter { (x, cell) -> cell == toFind }
            .map { (x, _) -> y to x }
    }

    val houses = listPositionsOf('1')
    val chickens = listPositionsOf('2')

    fun manhattanDistance(a: Pair<Int, Int>, b: Pair<Int, Int>) =
        abs(a.first - b.first) + abs(a.second - b.second)

    // house, chicken
    val distances = Array(houses.size) { h ->
        IntArray(chickens.size) { c ->
            manhattanDistance(houses[h], chickens[c])
        }
    }

    fun calculate(isChosen: BooleanArray): Int {
        val chosenChickens = chickens
            .indices
            .filter { isChosen[it] }

        return houses
            .indices
            .sumOf { h ->
                chosenChickens.minOf { c ->
                    distances[h][c]
                }
            }
    }

    fun permutation(nowChicken: Int, m: Int, isChosen: BooleanArray): Int {
        if (m == 0) {
            return calculate(isChosen)
        }

        if (nowChicken >= chickens.size)
            return Int.MAX_VALUE

        val chosen = permutation(nowChicken + 1, m - 1, isChosen.copyOf().apply { this[nowChicken] = true })
        val notChosen = permutation(nowChicken + 1, m, isChosen)

        return minOf(chosen, notChosen)
    }

    val result = permutation(0, m, BooleanArray(chickens.size))

    println(result)
}