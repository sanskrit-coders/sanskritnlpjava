package sanskritnlp.bot

import sanskritnlp.app._
import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot
import net.sourceforge.jwbf.core.contentRep.SimpleArticle
import org.slf4j.LoggerFactory

object Wiktionary {
  val log = LoggerFactory.getLogger(this.getClass)

  val userName = sanskritNlp.props.getProperty("WIKI_USER_NAME")
  // न भेतव्यम् इति केन दीक्षितेनोक्तम्?
  val passwd = "nIlakaNTha"
  val bot_wiktionary = new MediaWikiBot("http://sa.wiktionary.org/w/")

  def login = {
    log info userName + ":" + passwd
    bot_wiktionary.login(userName, passwd)
  }

  def test = {
    val article = bot_wiktionary.readData("Wiktionary:Sandbox123")
    log info article.getText()
    article.setText("यन्त्रेणेदं लिखितम्")
    log info article.getText()
  }

  def main(args: Array[String]): Unit = {
    login
    test
  }
}
