package fr.gplassard.adventofcode.day11

import scala.annotation.tailrec
import scala.io.Source

object Part1And2 extends App {

  val input = Source.fromInputStream(Part1And2.getClass.getClassLoader.getResourceAsStream("day11.txt")).getLines().flatMap(_.split(",")).toList

  def simplifyOpposits(a: String, b: String, group: Map[String, Int]): Map[String, Int] = {
    val as = group.getOrElse(a, 0)
    val bs = group.getOrElse(b, 0)

    if (as >= bs) {
      group
        .updated(a, as - bs)
        .updated(b, 0)
    }
    else {
      group
        .updated(a, 0)
        .updated(b, bs - as)
    }
  }

  def simplifyCombinaison(a: String, b: String, res: String, group: Map[String, Int]) = {
    val as = group.getOrElse(a, 0)
    val bs = group.getOrElse(b, 0)
    val ress = group.getOrElse(res, 0)

    if (as >= bs) {
      group
        .updated(a, as - bs)
        .updated(b, 0)
        .updated(res, ress + bs)
    }
    else {
      group
        .updated(a, 0)
        .updated(b, bs - as)
        .updated(res, ress + as)
    }
  }

  def solve(steps: List[String]): Int = solve(steps.groupBy(identity).mapValues(_.size))

  @tailrec
  def solve(map: Map[String, Int]): Int = {
    var group = map
    group = simplifyOpposits("nw", "se", group)
    group = simplifyOpposits("n", "s", group)
    group = simplifyOpposits("ne", "sw", group)

    group = simplifyCombinaison("se", "sw", "s", group)
    group = simplifyCombinaison("se", "n", "ne", group)
    group = simplifyCombinaison("sw", "n", "nw", group)
    group = simplifyCombinaison("s", "nw", "sw", group)
    group = simplifyCombinaison("s", "ne", "se", group)
    group = simplifyCombinaison("ne", "nw", "n", group)

    if (group.size < map.size) solve(group)
    else group.values.sum
  }

  println(solve(List("ne","ne","ne")))
  println(solve(List("ne","ne","sw","sw")))
  println(solve(List("ne","ne","s","s")))
  println(solve(List("se","sw","se","sw","sw")))
  println(solve(input))

  var max = 0
  for {
    i <- input.indices
  } {
    val res = solve(input.take(i))
    if (res > max) max = res
  }
  println(max)



}
