fun main(args: Array<String>) {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val weightToValues = (1..n)
        .map {
            readln().split(" ").map { it.toInt() }.let { it[0] to it[1] }
        }
        .filter { it.first <= k }

    val maxValueByWeight = weightToValues.fold(mutableMapOf<Int, Int>()) { maxValueByWeight, i ->
        val (weight, value) = i
        val newMaxValueByWeight = LinkedHashMap(maxValueByWeight)

        maxValueByWeight.plus(0 to 0).forEach {
            if (it.key + weight <= k) {
                newMaxValueByWeight[it.key + weight] =
                    maxOf(
                        maxValueByWeight[it.key + weight] ?: 0,
                        it.value + value,
                    )
            }
        }

        newMaxValueByWeight
    }

    val result = maxValueByWeight.values.maxOrNull() ?: 0
    println(result)
}
