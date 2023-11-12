fun isInRange(n: Int, r: Int, c: Int): Boolean =
    r in 0 until n && c in 0 until n

fun springAndSummer(
    r: Int,
    c: Int,
    nutrients: Array<IntArray>,
    trees: Array<Array<ArrayDeque<Int>>>,
) {
    val areaTrees = trees[r][c]

    // spring
    var newNutrient = 0
    repeat(areaTrees.size) {
        val age = areaTrees.removeFirst()
        if (nutrients[r][c] >= age) {
            nutrients[r][c] -= age
            areaTrees.addLast(age + 1)
        } else {
            newNutrient += age / 2
        }
    }

    // summer
    nutrients[r][c] += newNutrient
}

fun autumn(
    r: Int,
    c: Int,
    n: Int,
    trees: Array<Array<ArrayDeque<Int>>>,
) {
    val treesToReproduce = trees[r][c].count { it % 5 == 0 }
    if (treesToReproduce > 0) {
        val neighbors = listOf(
            r - 1 to c - 1,
            r - 1 to c,
            r - 1 to c + 1,
            r to c - 1,
            r to c + 1,
            r + 1 to c - 1,
            r + 1 to c,
            r + 1 to c + 1,
        )
        for ((nr, nc) in neighbors) {
            if (!isInRange(n, nr, nc))
                continue
            repeat(treesToReproduce) {
                trees[nr][nc].addFirst(1)
            }
        }
    }
}

fun year(n: Int, trees: Array<Array<ArrayDeque<Int>>>, nutrients: Array<IntArray>, aArray: Array<IntArray>) {
    for (r in 0 until n) {
        for (c in 0 until n) {
            springAndSummer(
                r = r,
                c = c,
                nutrients = nutrients,
                trees = trees
            )

        }
    }

    for (r in 0 until n) {
        for (c in 0 until n) {
            autumn(
                r = r,
                c = c,
                n = n,
                trees = trees
            )

            // winter
            nutrients[r][c] += aArray[r][c]
        }
    }
}

fun solve(n: Int, k: Int, aArray: Array<IntArray>, initialTrees: List<Triple<Int, Int, Int>>): Int {
    val nutrients = Array(n) { IntArray(n) { 5 } }
    val trees = Array(n) {
        Array(n) {
            // keep monotonic
            ArrayDeque<Int>()
        }
    }

    val initialTreesSortedByAge = initialTrees.sortedBy { it.third }
    for ((r, c, age) in initialTreesSortedByAge) {
        trees[r][c].addLast(age)
    }

    repeat(k) {
        year(
            n = n,
            trees = trees,
            nutrients = nutrients,
            aArray = aArray
        )
    }

    val result = trees.sumOf { row ->
        row.sumOf { deque ->
            deque.size
        }
    }

    return result
}

fun main() {
    val (n, m, k) = readln().split(" ").map { it.toInt() }
    val aArray = Array(n) {
        readln().split(" ").map { it.toInt() }.toIntArray()
    }
    val initialTrees = List(m) {
        readln().split(" ").map { it.toInt() }
            // r, c, age
            .let { Triple(it[0] - 1, it[1] - 1, it[2]) }
    }

    val result = solve(
        n = n,
        k = k,
        aArray = aArray,
        initialTrees = initialTrees
    )

    println(result)
}