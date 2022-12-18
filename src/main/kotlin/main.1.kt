import java.io.File

fun main(args: Array<String>) {
  val list = arrayListOf<List<Int>>()
  var curr = arrayListOf<Int>()
  list.add(curr)
  File("src/main/resources/data.1.txt").forEachLine {
    if (it.toString().isEmpty()) {
      curr = arrayListOf()
      list.add(curr)
    } else {
      curr.add(it.toInt())
    }
  }

  println(findHighestCalories(list))
  println(findTopThreeCalories(list))
}

fun findHighestCalories(list: List<List<Int>>): Int {
  //println(list)
  var max = 0
  for (elf in list) {
    max = maxOf(max, elf.sumOf { it })
  }
  return max
}

fun findTopThreeCalories(list: List<List<Int>>): Int {
  val map = list.map { it.sum() }
  //println(map.sortedDescending().subList(0, 3))
  return map.sortedDescending().subList(0, 3).sum()
}