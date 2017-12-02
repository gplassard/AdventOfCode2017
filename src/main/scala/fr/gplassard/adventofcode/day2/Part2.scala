package fr.gplassard.adventofcode.day2

import scala.io.Source

object Part2 extends App {

  val input = Source.fromInputStream(Part1.getClass.getClassLoader.getResourceAsStream("day2.txt")).getLines().map(_.split("\t").map(_.trim.toInt))

  val result = input.map { numbers =>

    val quotient = for {
      i <- 0 until numbers.length
      j <- i + 1 until numbers.length
      if numbers(i) % numbers(j) == 0 || numbers(j) % numbers(i) == 0
    } yield Math.max(numbers(i) / numbers(j), numbers(j) / numbers(i))

    quotient.head
  }.sum

  println(result)

}
