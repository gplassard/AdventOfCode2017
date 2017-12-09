package fr.gplassard.adventofcode.day9

import scala.util.parsing.combinator.RegexParsers

object GroupParser extends RegexParsers {

  case class Group(childrens: Seq[Group])

  object Group {
    def score(group: Group, level: Int): Int = {
      level + group.childrens.map(g => score(g, level + 1)).sum
    }
  }

  val negate: Parser[Any] = "!" ~ ".".r
  val notClosing: Parser[Any] = "[^>]".r

  val garbageContent: Parser[Any] = negate | notClosing

  val garbageParser: Parser[Any] = "<" ~ rep(garbageContent) ~ ">"

  lazy val groupeParser: Parser[Group] = "{" ~> rep(garbageParser) ~ repsep(groupeParser | "", "," ) ~ rep(garbageParser) <~ "}" ^^ {case _ ~ groups ~ _ => Group(groups.collect{case g: Group => g})}

  def apply(input: String): ParseResult[Group] = parseAll(groupeParser, input)
}
