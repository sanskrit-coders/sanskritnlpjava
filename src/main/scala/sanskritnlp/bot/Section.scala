package sanskritnlp.bot

import org.slf4j.LoggerFactory

import scala.annotation.tailrec
import scala.collection.mutable.ListBuffer

class Section {
  val log = LoggerFactory.getLogger(this.getClass)
  var title = ""
  var levelText = ""
  var headText = ""
  var subSections = ListBuffer[Section]()

  @tailrec final def getParentForSection(section: Section): Section = {
    if (subSections.size == 0 || section.levelText.length <= subSections.last.levelText.length) {
      if (section.levelText.size > levelText.size) {
        return this
      } else {
        return null
      }
    } else {
      return subSections.last.getParentForSection(section)
    }
  }

  def addSection(currentSection: Section) = {
    assert(currentSection != this)
    val parentSection = getParentForSection(currentSection)
    if (parentSection == null) {
      log info (currentSection.toString)
      log error("Cant find parent!")
    }
    parentSection.subSections += currentSection
    log info ("added section: " + currentSection)
  }

  override def toString: String = {
    var text = ""
    if(levelText.length > 0) {
      text = s"$levelText $title $levelText\n"
    }
    val subSectionText = subSections.map(_.toString).mkString("\n")
    text = s"$text$headText\n$subSectionText"
    return text
  }

  // Stops when a higher level section is encountered.
  // Returns number of lines consumed.
  def parse(titleIn: String = "", levelTextIn: String = "", lines: Array[String]): Unit = {
    title = titleIn
    levelText = levelTextIn
    headText = ""

    /*
    A valid Mediawiki section is a new line of the following form: =+[^=]+=+\s+.
    Note: there can't be space in the beginning, nor non space characters in the end.
    A malformed section has unequal prefix and suffix =-strings - in that case, Mediawiki simply uses the least number of equals to make sense of it.
     */
    val sectionPattern = "(=+)([^=]+)(=+)\\s*".r
    var currentSection = this
    lines.foreach(line =>
      line match {
        case sectionPattern(levelTextPre, titleMatch, levelTextSuffix) => {
          log info ("found section line!")
          if (currentSection != this) {
            addSection(currentSection)
          }

          currentSection = new Section
          if (levelTextPre.length <= levelTextSuffix.length) {
            currentSection.levelText = levelTextPre
          } else if (levelTextPre.length > levelTextSuffix.length) {
            currentSection.levelText = levelTextSuffix
          }
          currentSection.title = titleMatch.trim
          if (currentSection.levelText.length <= levelText.length) {
            return
          }
        }
        case _ => {
          currentSection.headText = currentSection.headText + s"$line\n"
          log info ("added line: " + line)
        }
      }
    )
    if (currentSection != this) {
      addSection(currentSection)
    }
    return
  }
}

object sectionTest {
  val log = LoggerFactory.getLogger(this.getClass)
  def main(args: Array[String]) {
    val wikiText =
      """
        |परीक्षार्थं सृष्टमिदम्।
        |== विभागः १ ==
        |असदफ़
        |
        |=== उपविभागः १ ===
        |
        |आषफ़द
        |असदफ़सद
        |
        |== विभागः २ ==
        |असदफ़
        |
        |= विभागः ३ =
        |असदफ़
        |
        |=== उपविभागः ३.१ ===
        |आषफ़द
        |===== उपविभागः ३.2 ===
        |आषफ़द
        |""".stripMargin
    val article = new Section()
    article.parse(lines = wikiText.split("\n"))
    log.info (article.toString)
  }
}