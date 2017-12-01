package fr.gplassard.adventofcode.day1

import scala.io.Source

object Part1 extends App {

  val input = Source.fromInputStream(Part1.getClass.getClassLoader.getResourceAsStream("day1_part1.txt")).getLines().mkString.toList

  val offseted = input.tail :+ input.head

  val result = input.zip(offseted)
    .filter(tuple => tuple._1 == tuple._2)
    .map(_._1.toString.toInt)
    .sum

  println(result)
}
