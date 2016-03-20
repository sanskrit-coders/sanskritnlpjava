package sanskritnlp.dictionary

import java.io.{File, PrintWriter}

import sanskritnlp.wiki.bot.wiktionary._

import scala.collection.immutable.HashMap
import scala.collection.mutable
import scala.io.{BufferedSource, Source}

class BabylonDictionary(name_in: String, source_in: String = "") {
  var wordToLocation = new HashMap[String, Int]

  val dict_name = name_in
  val source = source_in
  var fileLocation = ""

  // Maintained by external users of this dictionary - not here. Kept here for conveneince.
  val wordsSeen = new mutable.HashSet[String]

  var linesIter: Iterator[String] = null

  def fromFile(infileStr: String) = {
    fileLocation = infileStr
    val src = Source.fromFile(infileStr, "utf8")
    linesIter = src.getLines
  }

  def hasNext(): Boolean = {
    return linesIter.hasNext
  }

  def next(): (Array[String], String) = {
    val returnTuple = (linesIter.next().split('|'), linesIter.next)
    assert(linesIter.next() == "")
    return returnTuple
  }

  def makeWordToLocationMap() = {
    var word_index = 0
    while (hasNext()) {
      val (headwords, meaning) = next()
      word_index = word_index + 1
      // log.info(s"word_index : $word_index")
      val sktHeadwords = headwords.filter(_ matches "\\p{IsDevanagari}+")
      sktHeadwords.foreach(word => {
        wordToLocation += (word -> word_index)
      })
    }
  }
}
