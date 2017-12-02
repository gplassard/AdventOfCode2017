package fr.gplassard.adventofcode.day2

import scala.io.Source

object Part1 extends App {

  val input = Source.fromInputStream(Part1.getClass.getClassLoader.getResourceAsStream("day2.txt")).getLines().map(_.split("\t").map(_.trim.toInt))

  val result = input.map { line =>
    line.max - line.min
  }.sum

  println(result)

}
