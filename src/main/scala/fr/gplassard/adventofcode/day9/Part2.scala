package fr.gplassard.adventofcode.day9

import scala.io.Source

object Part2 extends App {
  val groups = Source.fromInputStream(Part1.getClass.getClassLoader.getResourceAsStream("day9.txt")).getLines()

  private val result = GroupParser(groups.mkString)
  println(result.get.garbage)
}
