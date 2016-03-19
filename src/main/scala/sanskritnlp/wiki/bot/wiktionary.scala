package sanskritnlp.wiki.bot

import sanskritnlp.dictionary.BabylonDictionary

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object wiktionary extends wikiBot {
  override val languageCode = "sa"
  override val wikiSiteName = "wiktionary"

  def addDictionaryMeaning(headwords: Array[String], meaning: String, sectionPath: String, dict_source: String, wordsSeen: mutable.HashSet[String]) = {
    log.info(headwords.mkString(","))
    val dict_name = sectionPath.split('/').last
    val head_text = s"{{फलकम्:यन्त्रशोधितकोशार्थः|कोशमूलम् = $dict_source}}"
    val tail_text = s"[[वर्गः: यन्त्रोपारोपितकोशांशः '$dict_name'-तः]]"
    val section_text = s"$meaning\n\n$tail_text"
    headwords.foreach(head => {
      if (wordsSeen contains head) {
        appendToSection(title = head, sectionPath = sectionPath, summary = "अर्थनिवेशः", text = s"\n\n$section_text" )
      } else {
        replaceSectionText(title = head, sectionPath = sectionPath, summary = "अर्थनिवेशः", text = s"$head_text\n\n$section_text")
      }
      // log info section_text
    })
  }

  def replaceBadText(headwords: Array[String]) = {
    log.info(headwords.mkString(","))
    headwords.foreach(head => {
      replaceRegex(head, "= कोशोद्धरणम् यन्त्रोपारोपितम् =", "= यन्त्रोपारोपितकोशांशः =")
    })
  }

  def uploadFromBabylonDict(filePath: String, sectionPath: String, dict_source: String, start_word_index: Int = 1, end_word_index: Int = 100000) = {
    val dictionary = new BabylonDictionary
    dictionary.fromFile(filePath)
    var word_index = 0
    var wordsSeen = new mutable.HashSet[String]
    while (dictionary.hasNext() && word_index + 1 <= end_word_index) {
      val (headwords, meaning) = dictionary.next()
      word_index = word_index + 1
      log.info(s"word_index : $word_index")
      if (word_index >= start_word_index) {
        // log.info(headwords.mkString(","))
        val sktHeadwords = headwords.filter(_ matches "\\p{IsDevanagari}+")
        addDictionaryMeaning(sktHeadwords, meaning, sectionPath, dict_source, wordsSeen)
        wordsSeen ++=  sktHeadwords
      }
    }
  }

  def main(args: Array[String]): Unit = {
    passwd = ""
    login
    // testEditSection 7031
    uploadFromBabylonDict(filePath = "/home/vvasuki/stardict-sanskrit/sa-head/kalpadruma-sa/kalpadruma-sa.babylon_final",
      sectionPath = "/यन्त्रोपारोपितकोशांशः/कल्पद्रुमः",
      start_word_index = 33560, end_word_index =  100000, dict_source = "http://www.sanskrit-lexicon.uni-koeln.de/scans/csldoc/contrib/index.html")
  }
}
