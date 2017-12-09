package fr.gplassard.adventofcode.day7

import scala.io.Source

object Part2 extends App {

  val programs = Source.fromInputStream(Part2.getClass.getClassLoader.getResourceAsStream("day7.txt")).getLines()
      .map(line => {
        val split = line.split(" ")
        val name = split(0)
        val weight = split(1).drop(1).dropRight(1).toInt
        val childrens = if (split.size > 3) split.drop(3).map(_.filterNot(_ == ',')).toSet else Set.empty[String]
        Program(name, weight, childrens)
      }).map(p => p.name -> p).toMap

  def weight(program: Program): Int = program.weight + program.childrensNames.flatMap(programs.get).map(_.weight).sum

  val childToParent = programs.values
    .map(p => p -> p.childrensNames)
    .flatMap{case (parent, childrens) => childrens.map(child => child -> parent)}
    .toMap


  def buildNode(name: String): Node = {
    val program = programs(name)
    val childrens = program.childrensNames.map(buildNode)
    Node(program, childrens.toSeq)
  }

  println(buildNode("qibuqqg"))
}

case class Node(program: Program, childrens: Seq[Node]) {
  def weight: Int = program.weight + childrens.map(_.weight).sum

  override def toString: String = s"Node(${program.name}, $weight, $childrens)"
}