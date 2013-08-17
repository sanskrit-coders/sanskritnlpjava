package sanskritnlp.bot

import sanskritnlp.app._
//import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot
//import net.sourceforge.jwbf.core.contentRep.SimpleArticle
import org.slf4j.LoggerFactory

class Wiktionary {
val log = LoggerFactory.getLogger(this.getClass)

val userName = sanskritNlp.props.getProperty("WIKI_USER_NAME")
val passwd = sanskritNlp.props.getProperty("WIKI_PASSWORD")
log info userName + ":" + passwd
//val bot = new MediaWikiBot("http://sa.wikipedia.org/w/")
//bot.login(userName, passwd)
//
//def test = {
//  val article = bot.readContent("Wiktionary:Sandbox")
//  log info(article.getText());
//}

}
