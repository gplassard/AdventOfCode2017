package fr.gplassard.adventofcode.day8

import scala.io.Source

object Part1 extends App {
  val programs = Source.fromInputStream(Part1.getClass.getClassLoader.getResourceAsStream("day8.txt")).getLines()

  val signals: Iterator[SignalParser.Signal] = programs.map(SignalParser.apply).map(_.get)

  val result = signals.foldLeft(Map.empty[String, Int].withDefaultValue(0)) { case (register, signal) =>
    signal.execute(register)
  }

  println(result.values.max)

}
