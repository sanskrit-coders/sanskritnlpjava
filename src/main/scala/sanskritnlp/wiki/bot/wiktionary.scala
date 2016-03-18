package sanskritnlp.wiki.bot

object wiktionary extends wikiBot {
  override val languageCode = "sa"
  override val wikiSiteName = "wiktionary"

  def main(args: Array[String]): Unit = {
    passwd = ""
    login
    testEditSection
  }
}
