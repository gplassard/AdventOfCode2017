package fr.gplassard.adventofcode.day10

import scala.io.Source

object Part1 extends App {

  val lengths = Source.fromInputStream(Part1.getClass.getClassLoader.getResourceAsStream("day10.txt")).getLines().flatMap(_.split(",")).map(_.toInt)

  var currentPosition = 0
  var skipSize = 0
  val list = scala.collection.mutable.MutableList(0 to 255:_*)

  def subList(list: List[Int], position: Int, length: Int) = {
    val firstSlice = list.slice(position, Math.min(position + length, list.length))

    if (firstSlice.lengthCompare(length) < 0)
      firstSlice ++ list.slice(0, length - firstSlice.size)
    else
      firstSlice
  }

  for {
    length <- lengths
  } {
    val sublist = subList(list.toList, currentPosition, length).reverse

    var index = currentPosition
    for {
      item <- sublist
    } {
      list(index) = item
      index = (index + 1) % list.size
    }
    currentPosition = (currentPosition + length + skipSize) % list.size
    skipSize += 1
  }

  println(list)
}
