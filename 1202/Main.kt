import java.util.*

fun <T> Iterator<T>.nextOrNull(): T? = if (hasNext()) next() else null

fun main(args: Array<String>) {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val sortedGems = (1..n)
        .map {
            readln().split(" ").map { it.toInt() }.let { it[0] to it[1] }
        }
        .sortedBy { it.first }
    val sortedBags = (1..k)
        .map { readln().toInt() }
        .sorted()

    val remainingGemsOrderByValue = PriorityQueue<Pair<Int, Int>>(compareByDescending { it.second })

    var result = 0L
    val gemIterator = sortedGems.iterator()
    val bagIterator = sortedBags.iterator()
    var gem = gemIterator.next()
    var bag: Int? = bagIterator.next()

    while (bag != null) {
        if (gem.first <= bag) {
            remainingGemsOrderByValue.add(gem)

            gem = gemIterator.nextOrNull()
                ?: (Int.MAX_VALUE to 0) // to check remaining bags without adding to PQ
        } else {
            if (remainingGemsOrderByValue.isNotEmpty())
                result += remainingGemsOrderByValue.poll().second

            bag = bagIterator.nextOrNull()
        }
    }

    println(result)
}