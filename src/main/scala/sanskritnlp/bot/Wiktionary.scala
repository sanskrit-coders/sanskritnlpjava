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

  def edit(bot: MediaWikiBot) = {
    val article = bot.readData("Wiktionary:Sandbox123")
    article.setText("यन्त्रेणेदं लिखितम्")
    article.setMinorEdit(false)
    article.setEditSummary("परीक्षा");
    bot.writeConarticle);
    log info article.getText()
  }

  def test(bot: MediaWikiBot) = {
    //Javadoc here: http://jwbf.sourceforge.net/doc/
    val article = bot.readData("Wiktionary:Sandbox123")
    log info article.getText()
    bot.delete("Wiktionary:Sandbox123")
  }

  def main(args: Array[String]): Unit = {
    login
    test(bot_wiktionary)
  }
}
