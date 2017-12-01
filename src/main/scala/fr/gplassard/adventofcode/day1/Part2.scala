package fr.gplassard.adventofcode.day1

import scala.io.Source


object Part2 extends App {

  val input = Source.fromInputStream(Part2.getClass.getClassLoader.getResourceAsStream("day1_part2.txt")).getLines().mkString.toList

  val offset = input.size / 2

  val offseted = input.drop(offset) :+ input.take(offset)

  val result = 2 * input.zip(offseted)
    .filter(tuple => tuple._1 == tuple._2)
    .map(_._1.toString.toInt)
    .sum

  println(result)
}
