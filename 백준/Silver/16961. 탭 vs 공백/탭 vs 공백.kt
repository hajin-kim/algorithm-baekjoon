fun main() {
    val n = readln().toInt()
    val reservations = List(n) {
        readln().split(" ").let {
            Triple(it[0][0], it[1].toInt(), it[2].toInt())
        }
    }

    val checkIn = reservations.groupBy { it.second }
    val checkOut = reservations.groupBy { it.third }

    var currentGuests = 0
    var tCount = 0
    var sCount = 0

    var daysWithGuest = 0
    var maxGuests = 0
    var daysWithPeace = 0
    var maxGuestsWithPeace = 0
    val longest = reservations.maxOf { it.third - it.second + 1 }

    fun existsGuest() = currentGuests > 0
    fun existsFight() = !(existsGuest() && tCount == sCount)

    fun updateStatistic() {
        maxGuests = maxOf(maxGuests, currentGuests)

        if (existsGuest()) {
            ++daysWithGuest
        }

        if (!existsFight()) {
            ++daysWithPeace
            maxGuestsWithPeace = maxOf(maxGuestsWithPeace, currentGuests)
        }
    }


    (1..366).forEach { day ->
        checkIn[day]?.forEach { (type, _, _) ->
            currentGuests++
            if (type == 'T')
                tCount++
            else
                sCount++
        }

        updateStatistic()

        checkOut[day]?.forEach { (type, _, _) ->
            currentGuests--
            if (type == 'T')
                tCount--
            else
                sCount--
        }

    }

    println(daysWithGuest)
    println(maxGuests)
    println(daysWithPeace)
    println(maxGuestsWithPeace)
    println(longest)
}
