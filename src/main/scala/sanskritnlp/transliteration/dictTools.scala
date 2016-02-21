package sanskritnlp.transliteration

/*
Run this:
PATH_TO_SANSKRITNLPJAVA=/home/vvasuki/sanskritnlpjava/target
scala -classpath "$PATH_TO_SANSKRITNLPJAVA/sanskritnlp-1.0-SNAPSHOT/WEB-INF/lib/*:$PATH_TO_SANSKRITNLPJAVA/sanskritnlp-1.0-SNAPSHOT/WEB-INF/classes" sanskritnlp.transliteration.dictTools.sutraNumbersToDevanagari  nyasa/nyasa.babylon
 */*/

import scala.io.Source
import java.io._
import sanskritnlp.transliteration.optitrans

/**
  * Created by vvasuki on 2/20/16.
  */
object dictTools {

  def sutraNumbersToDevanagari(infileStr: String): Unit = {
    println("Processing " + infileStr)
    val outfileStr = infileStr.replaceFirst(".babylon$", ".babylonv1")
    val src = Source.fromFile(infileStr, "utf8")
    val outFileObj = new File(outfileStr)
    new File(outFileObj.getParent).mkdirs
    val destination = new PrintWriter(outFileObj)

    val suutraPattern = """(\d+\.\d+\.\d+)""".r
    src.getLines.taforeach(line => {
      var newLine = suutraPattern.replaceAllIn(line, _ match {
        case suutraPattern(latin_str) => optitrans.toDevanagari(latin_str).replaceAll("।", ".")})
      destination.println(newLine)
      // println(line)
      // println(newLine)
    })
    destination.close()
    println("Produced" + outfileStr)
  }


  def asToDevanagari(infileStr: String): Unit = {
    println("Processing " + infileStr)
    val outfileStr = infileStr.replaceFirst(".babylon$", ".babylonv1")
    val src = Source.fromFile(infileStr, "utf8")
    val outFileObj = new File(outfileStr)
    new File(outFileObj.getParent).mkdirs
    val destination = new PrintWriter(outFileObj)

    val asPattern = """\{%(.+?)%\}""".r
    val asPatternUnmarked = """(\W)(\w+?\d\w*?)(\W)""".r
    src.getLines.foreach(line => {
      var newLine = asPattern.replaceAllIn(line, _ match {
        case asPattern(as_str) => as.toDevanagari(as_str)})
      newLine = asPatternUnmarked.replaceAllIn(newLine, _ match {
        case asPatternUnmarked(fore, as_str, aft) => fore + as.toDevanagari(as_str) + aft })
      destination.println(newLine)
      // println(line)
      println(newLine)
    })
    destination.close()
    println("Produced" + outfileStr)
  }
}
