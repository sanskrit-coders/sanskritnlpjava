package sanskritnlp.wiki.bot

import net.sourceforge.jwbf.core.contentRep.SimpleArticle
import org.slf4j.LoggerFactory
import sanskritnlp.dictionary.BabylonDictionary
import sanskritnlp.wiki.Section

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

trait wiktionary extends wikiBot {
  override val log = LoggerFactory.getLogger(this.getClass)
  override val wikiSiteName = "wiktionary"

  def replaceBadText(headwords: Array[String], regexMap: Map[String, String]) = {
    log.info(headwords.mkString(","))
    headwords.foreach(head => {
      replaceRegex(head, regexMap)
    })
  }

  def addDictionaryMeaning(head: String, meaning: String, dictionary: BabylonDictionary) = {
    val dict_source = dictionary.source
    log.info(s"Adding $head")
    val head_text = s"{{फलकम्:यन्त्रशोधितकोशार्थः|कोशमूलम् = $dict_source}}"
    val sectionPath = s"/यन्त्रोपारोपितकोशांशः/${dictionary.dict_name}"
    val category_name = sectionPath.split('/').filterNot(_ == "").mkString("-")
    val tail_text = s"[[वर्गः: $category_name]]"

    val section_text = s"$meaning\n\n$tail_text"
    if (dictionary.wordsSeen contains head) {
      appendToSection(title = head, sectionPath = sectionPath, summary = "अर्थनिवेशः", text = s"\n\n$section_text" )
    } else {
      replaceSectionText(title = head, sectionPath = sectionPath, summary = "अर्थनिवेशः", text = s"$head_text\n\n$section_text")
    }
    // log info section_text
    dictionary.wordsSeen += head
  }

  def deleteSection(headwords: Array[String], sectionPath: String): Unit = {
    headwords.foreach(head => {
      deleteSection(head, sectionPath)
    })
  }

  def uploadFromBabylonDict(dictionary: BabylonDictionary, headword_pattern: String = "\\p{IsDevanagari}+") = {
    dictionary.word_index = 0
    while (dictionary.hasNext() && dictionary.word_index + 1 <= dictionary.end_word_index) {
      val (headwords, meaning) = dictionary.next()
      dictionary.word_index = dictionary.word_index + 1
      log.info(s"${dictionary.dict_name} word_index : ${dictionary.word_index}")
      if (dictionary.word_index >= dictionary.start_word_index) {
        // log.info(headwords.mkString(","))
        val sktHeadwords = headwords.filter(_ matches headword_pattern)
        sktHeadwords.foreach(addDictionaryMeaning(_, meaning, dictionary))
      }
    }
  }

  def uploadFromBabylonDictsSerial(dictList: List[BabylonDictionary]) = {
    dictList.foreach(dictionary => {
      uploadFromBabylonDict(dictionary)
    })
  }


  def uploadFromBabylonDictsCombined(dictList: List[BabylonDictionary], headword_pattern: String = "\\p{IsDevanagari}+", start_index: Int = 1) = {
    val wordToDicts = new mutable.HashMap[String, ListBuffer[BabylonDictionary]]()
    dictList.foreach(dictionary => {
      dictionary.makeWordToLocationMap(headword_pattern = "\\p{IsDevanagari}+")
      dictionary.wordToLocations.keys.foreach(word => {
        var dictList = wordToDicts.getOrElse(word, ListBuffer[BabylonDictionary]())
        dictList += dictionary
        wordToDicts += (word -> dictList)
      })
    })

    var word_index = start_index - 1
    // use drop to skip n items.
    wordToDicts.keys.toList.sorted.drop(word_index).foreach(word => {
      word_index = word_index + 1
      log info s"$word (index: $word_index of ${wordToDicts.size}) is present in ${wordToDicts.getOrElse(word, Set[BabylonDictionary]()).map(_.dict_name).mkString(", ")}"
      val (article: SimpleArticle, articleSection: Section) = getArticleSection(word)
      wordToDicts.getOrElse(word, null).foreach(dictionary => {
        val (sectionPath, text) = getWikiEntry(dictionary, word)
        val section = articleSection.getOrCreateSection(sectionPath)
        section.headText = text
      })
      // log info articleSection.toString()
      editArticle(article = article, text = articleSection.toString, summary = s"(re)add ${dictList.mkString(", ")}")
    })
  }

  def getWikiEntry(dictionary: BabylonDictionary, word: String): (String, String) = null
}


object sa_wiktionary extends wiktionary {
  override val languageCode = "sa"
  override val log = LoggerFactory.getLogger(this.getClass)

  val kalpadruma = new BabylonDictionary(name_in = "कल्पद्रुमः", source_in = "http://www.sanskrit-lexicon.uni-koeln.de/scans/csldoc/contrib/index.html")
  kalpadruma.fromFile(infileStr = "/home/vvasuki/stardict-sanskrit/sa-head/kalpadruma-sa/kalpadruma-sa.babylon_final")
  val amara = new BabylonDictionary(name_in = "अमरकोशः", source_in = "http://github.com/sanskrit-coders/stardict-sanskrit/tree/master/sa-head/amara-onto")
  amara.fromFile(infileStr = "/home/vvasuki/stardict-sanskrit/sa-head/amara-onto/amara-onto.babylon_final")
  val vAcas = new BabylonDictionary(name_in = "वाचस्पत्यम्", source_in = "http://www.sanskrit-lexicon.uni-koeln.de/scans/csldoc/contrib/index.html")
  vAcas.fromFile(infileStr = "/home/vvasuki/stardict-sanskrit/sa-head/vAchaspatyam-sa/vAchaspatyam-sa.babylon_final")
  val mw = new BabylonDictionary(name_in = "Monier-Williams", source_in = "http://www.sanskrit-lexicon.uni-koeln.de/scans/csldoc/contrib/index.html")
  mw.fromFile(infileStr = "/home/vvasuki/stardict-sanskrit/sa-head/mw-sa/mw-sa.babylon_final")
  val apte = new BabylonDictionary(name_in = "Apte", source_in = "http://www.sanskrit-lexicon.uni-koeln.de/scans/csldoc/contrib/index.html")
  apte.fromFile(infileStr = "/home/vvasuki/stardict-sanskrit/sa-head/apte-sa/apte-sa.babylon_final")
  val shabdasAgara = new BabylonDictionary(name_in = "शब्दसागरः", source_in = "http://www.sanskrit-lexicon.uni-koeln.de/scans/csldoc/contrib/index.html")
  shabdasAgara.fromFile(infileStr = "/home/vvasuki/stardict-sanskrit/sa-head/shabda-sAgara/shabda-sAgara.babylon_final")

  override def getWikiEntry(dictionary: BabylonDictionary, word: String): (String, String) = {
    val head_text = s"{{फलकम्:यन्त्रशोधितकोशार्थः|कोशमूलम् = ${dictionary.source}}}"
    val sectionPath = s"/यन्त्रोपारोपितकोशांशः/${dictionary.dict_name}"
    val category_name = sectionPath.split('/').filterNot(_ == "").mkString("-")
    val tail_text = s"[[वर्गः: $category_name]]"
    val meanings = dictionary.getMeanings(word).mkString("\n\n")
    val text = s"$head_text\n\n$meanings\n\n$tail_text".replaceAll("\\{@|@\\}", "'''")
    return (sectionPath, text)
  }

  def main(args: Array[String]): Unit = {
    passwd = ""
    login
    uploadFromBabylonDictsCombined(List(vAcas, shabdasAgara, apte, mw), start_index = 1)
  }
}