package sanskritnlp.servlet
import sanskritnlp.vyAkaraNa._
import javax.servlet.http._
import org.apache.commons.lang3.StringEscapeUtils


class pratyAhArI extends HttpServlet {
  override def doPost(req: HttpServletRequest, resp: HttpServletResponse)
  {
    val txt_saMJNA = StringEscapeUtils.unescapeHtml4(req.getParameter("txt_saMJNA"))
    var matchShort = akSharasaMjJNA.fetch(txt_saMJNA, true).mkString(",")
    var matchLong = akSharasaMjJNA.fetch(txt_saMJNA, false).mkString(",")
    var txtOut = matchShort
    if(matchShort != matchLong) txtOut += "\n तथा- " + matchLong
    resp.setContentType("text/plain")
    resp.setCharacterEncoding("UTF-8")
    resp.getWriter().println(txtOut)
    println(txtOut)
  }
}
