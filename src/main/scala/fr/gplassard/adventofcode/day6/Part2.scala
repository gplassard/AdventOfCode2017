package fr.gplassard.adventofcode.day6

import scala.collection.mutable
import scala.io.Source

object Part2 extends App {

  val memory =  mutable.MutableList(Source.fromInputStream(Part2.getClass.getClassLoader.getResourceAsStream("day6.txt")).getLines().flatMap(_.split("\t")).map(_.toInt).toList: _*)

  var knowns = mutable.MutableList.empty[String]

  var counter = 0
  var firstIndex = 0
  while (knowns.count(_ == memory.mkString(",")) < 2) {
    if (firstIndex == 0 && knowns.contains(memory.mkString(","))) {
      firstIndex = counter
    }
    knowns += memory.mkString(",")
    val max = memory.max
    val maxIndex = memory.indexOf(max)
    memory(maxIndex) = 0
    var index = maxIndex
    for {
      _ <- 0 until max
    } {
      index = if (index + 1 < memory.size) index + 1 else 0
      memory(index) += 1
    }
    counter += 1
  }

  println(counter - firstIndex)


}
