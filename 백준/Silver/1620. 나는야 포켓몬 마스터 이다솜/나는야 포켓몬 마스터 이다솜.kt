fun solve(pokemons: Array<String>, queries: Array<String>): List<String> {
    val indexByPokemon = pokemons
        .withIndex()
        .associate { (index, value) -> value to index + 1 }

    return queries.map { query ->
        if (query[0].isDigit()) {
            pokemons[query.toInt() - 1]
        } else {
            indexByPokemon[query]!!.toString()
        }
    }
}

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val pokemons = Array(n) {
        readln()
    }
    val queries = Array(m) {
        readln()
    }

    val result = solve(pokemons, queries)

    println(result.joinToString("\n"))
}