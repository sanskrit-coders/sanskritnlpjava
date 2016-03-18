package sanskritnlp.dictionary

import java.io.{PrintWriter, File}

import scala.collection.immutable.HashMap
import scala.io.{BufferedSource, Source}

class BabylonDictionary() {
  var wordToMeaningMap = new HashMap[String, String]

  var linesIter: Iterator[String] = null
  def fromFile(infileStr: String) = {
    val src = Source.fromFile(infileStr, "utf8")
    linesIter = src.getLines
  }
  def hasNext(): Boolean = {
    return linesIter.hasNext
  }
  def next(): (Array[String], String) = {
    val returnTuple = (linesIter.next().split("|"), linesIter.next)
    assert(linesIter.next() == "")
    return returnTuple
  }
}
