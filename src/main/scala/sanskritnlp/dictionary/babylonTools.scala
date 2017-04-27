package sanskritnlp.dictionary

/*
Run this:
PATH_TO_SANSKRITNLPJAVA=/home/vvasuki/sanskritnlpjava/target
scala -classpath "$PATH_TO_SANSKRITNLPJAVA/sanskritnlp-1.0-SNAPSHOT/WEB-INF/lib/*:$PATH_TO_SANSKRITNLPJAVA/sanskritnlp-1.0-SNAPSHOT/WEB-INF/classes" sanskritnlp.transliteration.dictTools.sutraNumbersToDevanagari  nyasa/nyasa.babylon
 */*/

import java.io._

import org.slf4j.LoggerFactory
import sanskritnlp.transliteration.{as, optitrans, transliterator, iast}

import scala.io.Source

/**
  * Created by vvasuki on 2/20/16.
  */
object babylonTools {

  val log = LoggerFactory.getLogger("babylonTools")
  def sutraNumbersToDevanagari(infileStr: String): Unit = {
    println("Processing " + infileStr)
    val outfileStr = infileStr.replaceFirst(".babylon$", ".babylon_dev_sutra")
    val src = Source.fromFile(infileStr, "utf8")
    val outFileObj = new File(outfileStr)
    new File(outFileObj.getParent).mkdirs
    val destination = new PrintWriter(outFileObj)

    val suutraPattern = """(\d+\.\d+\.\d+)""".r
    src.getLines.foreach(line => {
      var newLine = suutraPattern.replaceAllIn(line, _ match {
        case suutraPattern(latin_str) => optitrans.toDevanagari(latin_str).get.replaceAll("।", ".")})
      destination.println(newLine)
      // println(line)
      // println(newLine)
    })
    destination.close()
    println("Produced " + outfileStr)
  }

  def fixHeadwords(infileStr: String, outputExt: String, headwordTransformer: (Array[String]) => Array[String]): Unit = {
    println("Processing " + infileStr)
    val outfileStr = infileStr.replaceFirst("\\.[^.]+$", outputExt)
    println("Will produce " + outfileStr)
    val src = Source.fromFile(infileStr, "utf8")
    val outFileObj = new File(outfileStr)
    new File(outFileObj.getParent).mkdirs
    val destination = new PrintWriter(outFileObj)

    src.getLines.zipWithIndex.foreach( t => {
      val line = t._1
      val index = t._2
      try {
        if(index % 3 == 0) {
          val headwords_original = line.split('|')
          val headwords_transliterated = headwordTransformer(headwords_original)
          destination.println((headwords_transliterated).toSet.toList.sorted.mkString("|"))
        } else {
          destination.println(line)
        }
      } catch {
        case ex: Exception => {
          log error ex.toString
          log error "line: " + t.toString()
        }
      }
    })
    destination.close()
    println("Produced " + outfileStr)
  }

  def fixEntries(infileStr: String, outputExt: String, entryTransformer: String => String): Unit = {
    println("Processing " + infileStr)
    val outfileStr = infileStr.replaceFirst("\\.[^.]+$", outputExt)
    println("Will produce " + outfileStr)
    val src = Source.fromFile(infileStr, "utf8")
    val outFileObj = new File(outfileStr)
    new File(outFileObj.getParent).mkdirs
    val destination = new PrintWriter(outFileObj)

    src.getLines.zipWithIndex.foreach( t => {
      val line = t._1
      val index = t._2
      try {
        if((index + 1) % 2 == 0) {
          val entryOriginal = line
          val entryTransliterated = entryTransformer(entryOriginal)
          destination.println(entryTransliterated.toSet.toList.sorted.mkString("|"))
        } else {
          destination.println(line)
        }
      } catch {
        case ex: Exception => {
          log error ex.toString
          log error "line: " + t.toString()
        }
      }
    })
    destination.close()
    println("Produced " + outfileStr)
  }

  def asToDevanagari(infileStr: String): Unit = {
    println("Processing " + infileStr)
    val outfileStr = infileStr.replaceFirst("\\.babylon$", ".babylonv1")
    val src = Source.fromFile(infileStr, "utf8")
    val outFileObj = new File(outfileStr)
    new File(outFileObj.getParent).mkdirs
    val destination = new PrintWriter(outFileObj)

    val asPattern = """\{%(.+?)%\}""".r
    val asPatternUnmarked = """(\W)(\w+?\d\w*?)(\W)""".r
    src.getLines.foreach(line => {
      var newLine = asPattern.replaceAllIn(line, _ match {
        case asPattern(as_str) => as.toDevanagari(as_str).get})
      newLine = asPatternUnmarked.replaceAllIn(newLine, _ match {
        case asPatternUnmarked(fore, as_str, aft) => fore + as.toDevanagari(as_str) + aft })
      destination.println(newLine)
      // println(line)
      println(newLine)
    })
    destination.close()
    println("Produced " + outfileStr)
  }
}
