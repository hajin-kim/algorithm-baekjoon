fun main() {
    val price = readln().toInt()
    var charge = 1000 - price
    var result = 0

    listOf(500, 100, 50, 10, 5, 1).forEach {
        result += charge / it
        charge %= it
    }

    println(result)
}