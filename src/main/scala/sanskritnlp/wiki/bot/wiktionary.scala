package sanskritnlp.wiki.bot

import sanskritnlp.dictionary.BabylonDictionary

object wiktionary extends wikiBot {
  override val languageCode = "sa"
  override val wikiSiteName = "wiktionary"

  def addDictionaryMeaning(headwords: Array[String], meaning: String, sectionPath: String, dict_source: String) = {
    log.info(headwords.mkString(","))
    val dict_name = sectionPath.split('/').last
    val head_text = s"{{फलकम्:यन्त्रशोधितकोशार्थः|कोशमूलम् = $dict_source}}"
    val tail_text = s"[[वर्गः: यन्त्रोपारोपितकोशांशः '$dict_name'-तः]]"
    val section_text = s"$head_text\n\n$meaning\n\n$tail_text"
    headwords.foreach(head => {
      // editSection(title = head, sectionPath = sectionPath, summary = "अर्थनिवेशः", text = section_text)
      deleteSection(head, sectionPath)
      // log info section_text
    })
  }

  def uploadFromBabylonDict(filePath: String, sectionPath: String, dict_source: String, start_word_index: Int = 1, end_word_index: Int = 100000) = {
    val dictionary = new BabylonDictionary
    dictionary.fromFile(filePath)
    var word_index = 0
    while (dictionary.hasNext() && word_index + 1 <= end_word_index) {
      val (headwords, meaning) = dictionary.next()
      word_index = word_index + 1
      log.info(s"word_index : $word_index")
      if (word_index >= start_word_index) {
        // log.info(headwords.mkString(","))
        val sktHeadwords = headwords.filter(_ matches "\\p{IsDevanagari}+")
        addDictionaryMeaning(sktHeadwords, meaning, sectionPath, dict_source)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    passwd = ""
    login
    // testEditSection
    uploadFromBabylonDict(filePath = "/home/vvasuki/stardict-sanskrit/sa-head/kalpadruma-sa/kalpadruma-sa.babylon_final",
      sectionPath = "/कोशोद्धरणम् यन्त्रोपारोपितम्/कल्पद्रुमः",
      start_word_index = 1, end_word_index =  4, dict_source = "http://www.sanskrit-lexicon.uni-koeln.de/scans/csldoc/contrib/index.html")
  }
}
