import java.util.*

fun main(args: Array<String>) {
    val n = readln().toInt()

    // base case
    if (n == 1) {
        println(0)
        return
    }

    var firstVotes = readln().toInt()
    val otherVotes = (2..n).map { readln().toInt() }
    val otherVotesPriorityQueue: PriorityQueue<Int> = PriorityQueue<Int>(otherVotes.size, Collections.reverseOrder())
    otherVotesPriorityQueue.addAll(otherVotes)

    var answer = 0
    while (firstVotes <= otherVotesPriorityQueue.peek()) {
        ++firstVotes
        ++answer
        otherVotesPriorityQueue.add(otherVotesPriorityQueue.poll() - 1)
    }

    println(answer)
}
