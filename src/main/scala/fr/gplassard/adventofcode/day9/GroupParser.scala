package fr.gplassard.adventofcode.day9

import scala.util.parsing.combinator.RegexParsers

object GroupParser extends RegexParsers {
  override def skipWhitespace: Boolean = false

  case class Group(childrens: Seq[Group], garbageScore: Int) {
    def garbage: Int = garbageScore + childrens.map(_.garbage).sum
  }

  object Group {
    def score(group: Group, level: Int): Int = {
      level + group.childrens.map(g => score(g, level + 1)).sum
    }
  }

  val negate: Parser[Int] = "!" ~ ".".r ^^^ 0
  val notClosing: Parser[Int] = "[^>]".r ^^^ 1

  val garbageContent: Parser[Int] = negate | notClosing

  val garbageParser: Parser[Int] = "<" ~> rep(garbageContent) <~ ">" ^^ {_.sum}

  lazy val groupeParser: Parser[Group] = "{" ~> rep(garbageParser) ~ repsep(groupeParser | "", "," ) ~ rep(garbageParser) <~ "}" ^^ {case g1 ~ groups ~ g2 => {
    Group(groups.collect{case g: Group => g}, g1.sum + g2.sum)
  }}

  def apply(input: String): ParseResult[Group] = parseAll(groupeParser, input)
}
