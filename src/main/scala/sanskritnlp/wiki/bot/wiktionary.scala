package sanskritnlp.wiki.bot

object wiktionary extends wikiBot {
  override val languageCode = "sa"
  override val wikiSiteName = "wiktionary"
  override val sandboxPage: String = s"$wikiSiteName:Sandbox"

  def main(args: Array[String]): Unit = {
    passwd = ""
    login
    testEditSection
  }
}
