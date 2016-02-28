package sanskritnlp.bot

import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot

object wiktionary extends wikiBot {
  override val bot = new MediaWikiBot("http://sa.wiktionary.org/w/")

  def main(args: Array[String]): Unit = {
    login
    test
  }
}
