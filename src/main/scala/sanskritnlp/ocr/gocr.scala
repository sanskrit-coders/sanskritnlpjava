package sanskritnlp.ocr

import org.slf4j.LoggerFactory
import sanskritnlp.transliteration.harvardKyoto

import scala.io.Source
import scala.util.matching.Regex

class GocrOutputIterator(ocrFileName: String) {
  val log = LoggerFactory.getLogger(this.getClass)

  val linesIter = Source.fromFile(ocrFileName, "utf8").getLines()

  // identify lines like:
  // {{{{{{/cns/oe-d/home/vvasuki/abhyankar/abhyankar_images-004.png}}}}}}
  val imageIdPattern = """\{\{\{\{\{\{.+-(\d\d\d)\.png\}\}\}\}\}\}""".r

  def pageNumFromLine(line: String): Int = {
    line match {
      case imageIdPattern(matched) => {
        return matched.toInt
      }
    }
  }

  var pagesElapsed = 0

  def hasNext: Boolean = linesIter.hasNext

  def logPagesElapsed = {
    log info s"Pages elapsed: $pagesElapsed"
  }

  def skipNPages(numToSkip: Int) = {
    logPagesElapsed
    Range(1, numToSkip + 1).foreach(x =>
      if (hasNext) {
        nextPage
      }
    )
    logPagesElapsed
  }

  def nextPage: String = {
    if(!linesIter.hasNext) {
      return ""
    }
    if (pagesElapsed == 0) {
      linesIter.find(_.matches(imageIdPattern.toString()))
    }
    pagesElapsed = pagesElapsed + 1
    // This consumes the next page line in iter2 as well!
    val pageLines = linesIter.takeWhile(!_.matches(imageIdPattern.toString()))
    return pageLines.fold("")((a, b) => a + "\n" + b)
  }
}

object gocr {
  val log = LoggerFactory.getLogger(this.getClass)
  def main(args: Array[String]) {
    val gocrOut = new GocrOutputIterator("/home/vvasuki/sanskrit-ocr-r0/vaak/vyAkaraNam/abhyankar-grammar/abhyankar-grammar-gocr.txt")

    var page = ""
    while (gocrOut.hasNext && gocrOut.pagesElapsed < 10) {
      page = gocrOut.nextPage
      gocrOut.logPagesElapsed
      log info page
    }
  }
}
