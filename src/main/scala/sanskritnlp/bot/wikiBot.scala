package sanskritnlp.bot


import sanskritnlp.app._
import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot
import net.sourceforge.jwbf.core.contentRep.SimpleArticle
import org.slf4j.LoggerFactory
import sanskritnlp.app.sanskritNlp

trait wikiBot {
  val log = LoggerFactory.getLogger(this.getClass)

  val userName = sanskritNlp.props.getProperty("WIKI_USER_NAME")
  var passwd = ""
  val bot: MediaWikiBot = null

  def login = {
    passwd = ""
    // न भेतव्यम् इति केन दीक्षितेनोक्तम्?
    if (passwd.isEmpty) {
      passwd = readLine("passwd?").trim
    }
    log info userName + ":" + passwd
    bot.login(userName, passwd)
  }

  def edit(title: String, text: String, summary: String, isMinor: Boolean = false) = {
    val article = bot.readData(title)
    article.setText(text)
    article.setEditSummary(summary)
    bot writeContent article
    // log info article.getText()
  }

  def test() = {
    //Javadoc here: http://jwbf.sourceforge.net/doc/
    val article = bot.readData("Wiktionary:Sandbox")
    log info article.getText()
    // bot.delete("Wiktionary:Sandbox")
  }

}
