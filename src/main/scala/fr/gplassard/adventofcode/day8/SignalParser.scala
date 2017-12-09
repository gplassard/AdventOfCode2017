package fr.gplassard.adventofcode.day8

import scala.util.parsing.combinator.RegexParsers

object SignalParser extends RegexParsers {

  case class Signal(key: String, increment: Boolean, amount: Int, condition: Condition) {
    def execute(register: Map[String, Int]): Map[String, Int] = {
      if (condition.evaluate(register)) {
        val toAdd = if (increment) amount else - amount
        register.updated(key, register(key) + toAdd)
      }
      else register
    }
  }

  case class Condition(key: String, comparison: Comparison, amount: Int) {
    def evaluate(register: Map[String, Int]): Boolean = comparison.evaluate(register(key), amount)
  }

  sealed trait Comparison {
    def evaluate(a: Int, b: Int): Boolean
  }
  case object GT extends Comparison {
    override def evaluate(a: Int, b: Int): Boolean = a > b
  }
  case object GTE extends Comparison {
    override def evaluate(a: Int, b: Int): Boolean = a >= b
  }
  case object LT extends Comparison {
    override def evaluate(a: Int, b: Int): Boolean = a < b
  }
  case object LTE extends Comparison {
    override def evaluate(a: Int, b: Int): Boolean = a <= b
  }
  case object EQ extends Comparison {
    override def evaluate(a: Int, b: Int): Boolean = a == b
  }
  case object NEQ extends Comparison {
    override def evaluate(a: Int, b: Int): Boolean = a != b
  }

  val gte: Parser[Comparison] = ">=" ^^^ GTE
  val gt: Parser[Comparison] = ">" ^^^ GT
  val lte: Parser[Comparison] = "<=" ^^^ LTE
  val lt: Parser[Comparison] = "<" ^^^ LT
  val eq: Parser[Comparison] = "==" ^^^ EQ
  val neq: Parser[Comparison] = "!=" ^^^ NEQ

  val comparison: Parser[Comparison] = gte | gt | lte | lt | eq | neq
  val condition: Parser[Condition] = "\\w+".r ~ comparison ~ "-?\\d+".r ^^ {case r ~ c ~ a => Condition(r, c, a.toInt)}
  val signal: Parser[Signal] = "\\w+".r ~ ("inc" | "dec") ~ "-?\\d+".r ~ "if" ~ condition ^^ {case r ~ i ~ a ~ _ ~ c => Signal(r, i == "inc", a.toInt, c)}

  def apply(input: String): ParseResult[Signal] = parseAll(signal, input)
}
