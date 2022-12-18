import java.io.File

fun main(args: Array<String>) {
  val list = arrayListOf<List<String>>()
  File("src/main/resources/data.2.txt").forEachLine {
    list.add(it.split(" "))
  }

  println(list)
  list.forEach { println("${it} : ${evaluateRound(it)}") }
  println(calculateScore(list))
  println(calculateScore2(list))
}

// A for Rock, B for Paper, and C for Scissors.
// X for Rock, Y for Paper, and Z for Scissors
//The score for a single round is the score for the shape you selected (1 for Rock, 2 for Paper, and 3 for Scissors)
// plus the score for the outcome of the round (0 if you lost, 3 if the round was a draw, and 6 if you won).

fun calculateScore(list: List<List<String>>): Int {
  return list.map { evaluateRound(it) }.sum()
}

fun calculateScore2(list: List<List<String>>): Int {
  return list.map { decideRounds(it) }.map { evaluateRound(it) }.sum()
}

fun evaluateRound(play: List<String>): Int {
  var oppoPlay = play.get(0).toCharArray().get(0) - 'A' + 1
  var shapeScore = play.get(1).toCharArray().get(0) - 'X' + 1

  var outcomeScore = 0
  if (oppoPlay == shapeScore) outcomeScore = 3
  else if (shapeScore == ((oppoPlay % 3) + 1)) outcomeScore = 6

  return shapeScore + outcomeScore
}

//"Anyway, the second column says how the round needs to end:
// X means you need to lose, Y means you need to end the round in a draw, and Z means you need to win. Good luck!"
fun decideRounds(round: List<String>): List<String> {
  val oppoPlay = round.get(0).toCharArray().get(0)
  val intent = round.get(1).toCharArray().get(0)
  val oppIndex = oppoPlay - 'A'
  var choice = when (intent) {
    'X' -> 'X' + oppIndex - 1
    'Z' -> 'X' + oppIndex + 1
    else -> 'X' + oppIndex
  }
  if (choice > 'Z') choice = 'X'
  if (choice < 'X') choice = 'Z'

  return arrayListOf(oppoPlay.toString(), choice.toString())
}