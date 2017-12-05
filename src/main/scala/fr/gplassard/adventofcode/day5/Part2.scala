package fr.gplassard.adventofcode.day5

import scala.collection.mutable
import scala.io.Source

object Part2 extends App {

  val input = mutable.MutableList(Source.fromInputStream(Part2.getClass.getClassLoader.getResourceAsStream("day5.txt")).getLines().map(_.toInt).toList:_*)

  var index = 0
  var step = 0

  while (index >= 0 && index < input.size) {
    val nextIndex = index + input(index)
    input(index) += (if (input(index) >=3) -1 else 1)
    index = nextIndex
    step += 1
  }

  println(step)

}
