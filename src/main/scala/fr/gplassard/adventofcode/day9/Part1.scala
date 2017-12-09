package fr.gplassard.adventofcode.day9

import fr.gplassard.adventofcode.day9.GroupParser.Group

import scala.io.Source

object Part1 extends App {
  val groups = Source.fromInputStream(Part1.getClass.getClassLoader.getResourceAsStream("day9.txt")).getLines()

  private val result = GroupParser(groups.mkString)
  println(result)

  println(Group.score(result.get, 1))
}
