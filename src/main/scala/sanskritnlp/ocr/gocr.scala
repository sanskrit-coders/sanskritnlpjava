package sanskritnlp.ocr

import org.slf4j.LoggerFactory
import sanskritnlp.transliteration.harvardKyoto

import scala.io.Source
import scala.util.matching.Regex

/**
  * Created by vvasuki on 2/28/16.
  */
object gocr {
  val log = LoggerFactory.getLogger(this.getClass)

  def getPages(ocrFileName: String): Iterator[Tuple2[Int, String]] = {
    val src = Source.fromFile(ocrFileName, "utf8")

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

    val (linesIter1, linesIter2) = src.getLines.duplicate
    linesIter2.find(_.matches(imageIdPattern.toString()))
    return linesIter1.filter(_.matches(imageIdPattern.toString())).map(pageNumLine => {
      // log info pageNumLine

      // This consumes the next page line in iter2!
      val pageLines = linesIter2.takeWhile(!_.matches(imageIdPattern.toString()))
      (pageNumFromLine(pageNumLine), pageLines.fold("")((a, b) => a + "\n" + b))
    })
  }

  def main(args: Array[String]) {
    val numToPageLinesMap =
      getPages("/home/vvasuki/sanskrit-ocr-r0/vaak/vyAkaraNam/abhyankar-grammar/abhyankar-grammar-gocr.txt")
    numToPageLinesMap.take(50).foreach(x => {
      log info x.toString()
    }
    )
  }
}
