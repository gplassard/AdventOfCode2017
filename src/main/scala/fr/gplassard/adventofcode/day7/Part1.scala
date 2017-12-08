package fr.gplassard.adventofcode.day7

import scala.io.Source

object Part1 extends App {

  val programs = Source.fromInputStream(Part1.getClass.getClassLoader.getResourceAsStream("day7.txt")).getLines()
      .map(line => {
        val split = line.split(" ")
        val name = split(0)
        val weight = split(1).drop(1).dropRight(1).toInt
        val childrens = if (split.size > 3) split.drop(3).map(_.filterNot(_ == ',')).toSet else Set.empty[String]
        Program(name, weight, childrens)
      }).toSet[Program]

  val programWithParents = programs.flatMap(_.childrensNames)

  val bottom = programs.map(_.name).find(name => !programWithParents.contains(name))


  println(bottom)

}
