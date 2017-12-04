package fr.gplassard.adventofcode.day4

import scala.io.Source

object Part2 extends App {

  val input = Source.fromInputStream(Part2.getClass.getClassLoader.getResourceAsStream("day4.txt")).getLines()

  def isValid(passphrase: String): Boolean = {
    val split = passphrase.split(" ")
    val dups = for {
      i <- 0 until split.length
      j <- i + 1 until split.length
      if split(i).groupBy(identity) == split(j).groupBy(identity)
    } yield split(i)

    dups.isEmpty
  }

  val result = input.count(isValid)

  println(result)

}
