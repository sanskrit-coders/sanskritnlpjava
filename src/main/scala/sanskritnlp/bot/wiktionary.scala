package sanskritnlp.bot

import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot

object wiktionary extends wikiBot {
  override val languageCode = "sa"
  override val wikiSiteName = "wiktionary"

  def main(args: Array[String]): Unit = {
    login
    test
  }
}
