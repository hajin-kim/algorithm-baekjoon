val vowels = setOf('a', 'e', 'i', 'o', 'u')

fun generate(l: Int, c: Int, characters: CharArray, now: Int, selected: List<Char>) {
    if (selected.size == l) {
        val (vowels, consonants) = selected.partition { it in vowels }

        if (vowels.isNotEmpty() && consonants.size >= 2)
            println(selected.joinToString(""))

        return
    }

    if (now == characters.size)
        return

    generate(l, c, characters, now + 1, selected.plus(characters[now]))
    generate(l, c, characters, now + 1, selected)
}

fun main() {
    val (l, c) = readln().split(" ").map { it.toInt() }
    val characters = readln()
        .split(" ")
        .map { it.first() }
        .sorted()
        .toCharArray()

    generate(l, c, characters, 0, listOf())
}
