package sanskritnlp.bot

import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot

/**
  * Created by vvasuki on 2/28/16.
  */
object wikisource extends wikiBot {
  override val bot = new MediaWikiBot("http://sa.wikisource.org/w/")

  def main(args: Array[String]): Unit = {
    login
    test
  }
}
