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

  def addDictionaryMeaning(head: String, dictionary: BabylonDictionary) = {
    val dict_source = dictionary.source
    log.info(s"Adding $head")
    val (sectionPath, text) = getWikiEntry(dictionary, head)
    replaceSectionText(title = head, sectionPath = sectionPath, summary = "अर्थनिवेशः", text = text)
  }

  // Used dictionaryUsed's name instead of dictionary's name?
  def fixDictNameMixup(dictionary: BabylonDictionary, dictionaryUsed: BabylonDictionary, headword_pattern: String = "\\p{IsDevanagari}+") = {
    dictionaryUsed.makeWordToLocationMap(headword_pattern)
    dictionary.makeWordToLocationMap(headword_pattern)
    dictionary.getWords.foreach(word => {
      addDictionaryMeaning(word, dictionary)
      deleteSection(word, getSectionPath(dictionaryUsed))
      val meanings: ListBuffer[String] = dictionaryUsed.getMeanings(word)
      // log info meanings.mkString(", ")
      if ( meanings != null && meanings.size > 0) {
        log info s"found $word in ${dictionaryUsed.dict_name}"
        addDictionaryMeaning(word, dictionaryUsed)
      }
    })
  }

  def uploadFromBabylonDictsCombined(dictList: List[BabylonDictionary], start_index: Int = 1, end_index: Int = 1000000, headword_pattern: String) = {
    val wordToDicts = new mutable.HashMap[String, ListBuffer[BabylonDictionary]]()
    dictList.foreach(dictionary => {
      // dictionary.makeWordToLocationMap(headword_pattern = "\\p{IsDevanagari}+")
      dictionary.makeWordToMeaningsMap(headword_pattern)
      dictionary.getWords.foreach(word => {
        var dictList = wordToDicts.getOrElse(word, ListBuffer[BabylonDictionary]())
        dictList += dictionary
        wordToDicts += (word -> dictList)
      })
    })

    var word_index = start_index - 1
    // use drop to skip n items.
    wordToDicts.keys.toList.sorted.drop(word_index).take(end_index - start_index + 1).foreach(word => {
      word_index = word_index + 1
      log info s"$word (index: $word_index of $end_index <= ${wordToDicts.size}) is present in ${wordToDicts.getOrElse(word, Set[BabylonDictionary]()).map(_.dict_name).mkString(", ")}"
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
  def getSectionPath(dictionary: BabylonDictionary): (String) = null
}


object sa_wiktionary extends wiktionary {
  override val languageCode = "sa"
  override val log = LoggerFactory.getLogger(this.getClass)

  val kalpadruma = new BabylonDictionary(name_in = "कल्पद्रुमः", source_in = "http://www.sanskrit-lexicon.uni-koeln.de/scans/csldoc/contrib/index.html", head_language= "sa")
  kalpadruma.fromFile(infileStr = "/home/vvasuki/stardict-sanskrit/sa-head/kalpadruma-sa/kalpadruma-sa.babylon_final")
  val amara = new BabylonDictionary(name_in = "अमरकोशः", source_in = "http://github.com/sanskrit-coders/stardict-sanskrit/tree/master/sa-head/amara-onto", head_language= "sa")
  amara.fromFile(infileStr = "/home/vvasuki/stardict-sanskrit/sa-head/amara-onto/amara-onto.babylon_final")
  val vAcas = new BabylonDictionary(name_in = "वाचस्पत्यम्", source_in = "http://www.sanskrit-lexicon.uni-koeln.de/scans/csldoc/contrib/index.html", head_language= "sa")
  vAcas.fromFile(infileStr = "/home/vvasuki/stardict-sanskrit/sa-head/vAchaspatyam-sa/vAchaspatyam-sa.babylon_final")
  val mw = new BabylonDictionary(name_in = "Monier-Williams", source_in = "http://www.sanskrit-lexicon.uni-koeln.de/scans/csldoc/contrib/index.html", head_language= "sa")
  mw.fromFile(infileStr = "/home/vvasuki/stardict-sanskrit/sa-head/mw-sa/mw-sa.babylon_final")
  val apte = new BabylonDictionary(name_in = "Apte", source_in = "http://www.sanskrit-lexicon.uni-koeln.de/scans/csldoc/contrib/index.html", head_language= "sa")
  apte.fromFile(infileStr = "/home/vvasuki/stardict-sanskrit/sa-head/apte-sa/apte-sa.babylon_final")
  val shabdasAgara = new BabylonDictionary(name_in = "शब्दसागरः", source_in = "http://www.sanskrit-lexicon.uni-koeln.de/scans/csldoc/contrib/index.html", head_language= "sa")
  shabdasAgara.fromFile(infileStr = "/home/vvasuki/stardict-sanskrit/sa-head/shabda-sAgara/shabda-sAgara.babylon_final")
  val AkhyAtachandrikA = new BabylonDictionary(name_in = "आख्यातचन्द्रिका", source_in = "http://github.com/sanskrit-coders/stardict-sanskrit/tree/master/sa-head/AkhyAtachandrikA", head_language= "sa")
  AkhyAtachandrikA.fromFile(infileStr = "/home/vvasuki/stardict-sanskrit/sa-head/AkhyAtachandrikA/AkhyAtachandrikA.babylon_final")

  /* These don't yet exist:
  val mw_eng = new BabylonDictionary(name_in = "Monier Williams (en-sa)", source_in = "http://www.sanskrit-lexicon.uni-koeln.de/scans/csldoc/contrib/index.html", head_language= "en")
  mw_eng.fromFile(infileStr = "/home/vvasuki/stardict-sanskrit/en-head/mw-bi-itrans-dev/mw-bi-itrans-dev.babylon_final")
  val apte_eng = new BabylonDictionary(name_in = "Apte (en-sa)", source_in = "http://github.com/sanskrit-coders/stardict-sanskrit/tree/master/sa-head/AkhyAtachandrikA", head_language= "sa")
  apte_eng.fromFile(infileStr = "/home/vvasuki/stardict-sanskrit/en-head/apte-bi/apte-bi.babylon_final")
  */
  val computer_shrIkAnta = new BabylonDictionary(name_in = "आङ्ग्लसंस्कृतसङ्गणककोशः श्रीकान्तस्य", source_in = "http://github.com/sanskrit-coders/stardict-sanskrit/tree/master/en-head/computer-shrIkAnta", head_language= "en")
  computer_shrIkAnta.fromFile(infileStr = "/home/vvasuki/stardict-sanskrit/en-head/computer-shrIkAnta/computer-shrIkAnta.babylon")


  override def getSectionPath(dictionary: BabylonDictionary): (String) = s"/यन्त्रोपारोपितकोशांशः/${dictionary.dict_name}"
  override def getWikiEntry(dictionary: BabylonDictionary, word: String): (String, String) = {
    val head_text = s"{{फलकम्:यन्त्रशोधितकोशार्थः|कोशमूलम् = ${dictionary.source}}}"
    val sectionPath = getSectionPath(dictionary)
    val category_name = sectionPath.split('/').filterNot(_ == "").mkString("-")
    val tail_text = s"[[वर्गः: $category_name]]"
    val meanings = dictionary.getMeanings(word).mkString("\n\n")
    val text = s"$head_text\n\n$meanings\n\n$tail_text".replaceAll("\\{@|@\\}", "'''")
    return (sectionPath, text)
  }

  def main(args: Array[String]): Unit = {
    passwd = ""
    login
    uploadFromBabylonDictsCombined(dictList = List(computer_shrIkAnta), headword_pattern = "[a-z]+")
    // fixDictNameMixup(dictionary = AkhyAtachandrikA, dictionaryUsed = shabdasAgara, headword_pattern = "\\p{IsDevanagari}+")
  }
}