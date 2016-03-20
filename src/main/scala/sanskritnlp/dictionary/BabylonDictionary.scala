package sanskritnlp.dictionary

import java.io.{File, PrintWriter}

import org.slf4j.LoggerFactory
import sanskritnlp.wiki.bot.wiktionary._

import scala.collection.mutable.ListBuffer
import scala.collection.immutable.HashMap
import scala.collection.mutable
import scala.io.{BufferedSource, Source}

class BabylonDictionary(name_in: String, source_in: String = "") {
  var wordToLocations = new HashMap[String, ListBuffer[Int]]
  val log = LoggerFactory.getLogger(this.getClass)

  val dict_name = name_in
  val source = source_in
  var fileLocation = ""

  // Maintained by external users of this dictionary - not here. Kept here for conveneince.
  val wordsSeen = new mutable.HashSet[String]
  var start_word_index: Int = 1
  var end_word_index: Int = 100000
  var word_index = 0

  var linesIter: Iterator[String] = null

  def fromFile(infileStr: String) = {
    fileLocation = infileStr
    word_index = 0
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

  def take(entriesToSkip: Int) = {
    while(hasNext() && word_index < entriesToSkip) {
      next()
      word_index = word_index + 1
    }
  }

  def makeWordToLocationMap() = {
    word_index = 0
    while (hasNext()) {
      val (headwords, meaning) = next()
      word_index = word_index + 1
      // log.info(s"word_index : $word_index")
      val sktHeadwords = headwords.filter(_ matches "\\p{IsDevanagari}+")
      sktHeadwords.foreach(word => {
        val locus_list = wordToLocations.getOrElse(word, ListBuffer[Int]())
        locus_list += word_index
        wordToLocations += (word -> locus_list)
      })
    }
    fromFile(fileLocation)
  }

  def getMeaningAtIndex(locus: Int): String = {
    log info(s"locus: $locus")
    take(locus - 1)
    val (_, meaning_line) = next()
    fromFile(fileLocation)
    return meaning_line
  }

  def getMeanings(word: String): ListBuffer[String] = {
    if (wordToLocations.size == 0) {
      makeWordToLocationMap()
    }
    val definition_locus_list = wordToLocations.getOrElse(word, ListBuffer[Int]())
    return definition_locus_list.map(getMeaningAtIndex(_))
  }
}

object babylonDictTest {
  val log = LoggerFactory.getLogger(this.getClass)

  def main(args: Array[String]) {
    val kalpadruma = new BabylonDictionary(name_in = "कल्पद्रुमः", source_in = "http://www.sanskrit-lexicon.uni-koeln.de/scans/csldoc/contrib/index.html")
    kalpadruma.fromFile(infileStr = "/home/vvasuki/stardict-sanskrit/sa-head/kalpadruma-sa/kalpadruma-sa.babylon_final")
    log info kalpadruma.getMeanings("अ").mkString("\n\n")
  }
}