package sanskritnlp.bot


import java.text.SimpleDateFormat
import java.util.Calendar

import net.sourceforge.jwbf.mediawiki.actions.util.ApiException
import sanskritnlp.app._
import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot
import net.sourceforge.jwbf.core.contentRep.SimpleArticle
import org.slf4j.LoggerFactory
import sanskritnlp.app.sanskritNlp
import scala.annotation.tailrec

trait wikiBot {
  val log = LoggerFactory.getLogger(this.getClass)
  val languageCode: String = null
  val wikiSiteName: String = null
  var bot: MediaWikiBot = null

  val userName = sanskritNlp.props.getProperty("WIKI_USER_NAME")
  var passwd = ""
  val sandboxPage: String = s"$wikiSiteName:Sandbox"

  // Bot policy: https://en.wikipedia.org/wiki/Wikipedia:Bot_policy
  // see https://www.mediawiki.org/wiki/Manual:$wgRateLimits
  // But 60/8 results in rate limiting.
  val minGapBetweenEdits: Int = (math.ceil(60/1) + 1).toInt

  def login = {
    bot = new MediaWikiBot(s"http://$languageCode.$wikiSiteName.org/w/")
    // न भेतव्यम् इति केन दीक्षितेनोक्तम्?
    if (passwd.isEmpty) {
      passwd = readLine("passwd?").trim
    }
    log info userName + ":" + passwd
    bot.login(userName, passwd)
  }

  var prevEditTime = System.currentTimeMillis / 1000
  @tailrec final def edit(title: String, text: String, summary: String, isMinor: Boolean = false, num_retries: Int = 3): Unit = {
    try{
      val article = bot.readData(title)
      article.setText(text)
      article.setEditSummary(summary)

      // Deal with timeouts
      var nowTime = System.currentTimeMillis / 1000
      if (nowTime - prevEditTime < minGapBetweenEdits) {
        log info s"sleeping for $minGapBetweenEdits secs"
        Thread.sleep(minGapBetweenEdits * 1000)
      }

      prevEditTime = nowTime
      // Finally do the edit.
      bot writeContent article
      // log info article.getText()
    } catch {
      // To deal with java.lang.IllegalStateException: invalid status: HTTP/1.1 503 Service Unavailable.
      case e: IllegalStateException => {
        log.warn(e.getMessage)
        if (num_retries > 0) {
          edit(title = title, text = text, summary = summary, isMinor = isMinor, num_retries = num_retries - 1)
        }
      }
      // To deal with: net.sourceforge.jwbf.mediawiki.actions.util.ApiException: API ERROR CODE: badtoken VALUE: Invalid token
      case e: ApiException => {
        log.warn(e.getMessage)
        login
        if (num_retries > 0) {
          edit(title = title, text = text, summary = summary, isMinor = isMinor, num_retries = num_retries - 1)
        }
      }
    }
  }

  def test() = {
    //Javadoc here: http://jwbf.sourceforge.net/doc/
    val article = bot.readData(sandboxPage)
    log info "|" + article.getText() + "|"
    // bot.delete("Wiktionary:Sandbox")
  }

}
