package fr.gplassard.adventofcode.day8

import scala.io.Source

object Part2 extends App {
  val programs = Source.fromInputStream(Part2.getClass.getClassLoader.getResourceAsStream("day8.txt")).getLines()

  val signals: Iterator[SignalParser.Signal] = programs.map(SignalParser.apply).map(_.get)
  private val emptyRegister = Map.empty[String, Int].withDefaultValue(0)

  val result = signals.foldLeft((emptyRegister, 0)) { case ((register, max), signal) =>
    val newRegister = signal.execute(register)
    val registerMax = newRegister.values.max
    val newMax = if (registerMax > max) registerMax else max
    (newRegister, newMax)
  }

  println(result._2)

}
