package sanskritnlp.servlet
import sanskritnlp.transliteration._
import javax.servlet.http._
import org.apache.commons.lang3.StringEscapeUtils


class Transliterate extends HttpServlet {
  override def doPost(req: HttpServletRequest, resp: HttpServletResponse)
  {
    req.setCharacterEncoding("UTF-8");
    println(req.getParameterMap.values.toArray.toList.mkString("_"))
    val optScriptFrom = StringEscapeUtils.unescapeHtml4(req.getParameter("optScriptFrom"))
    val optScriptTo = StringEscapeUtils.unescapeHtml4(req.getParameter("optScriptTo"))
    val txtIn = StringEscapeUtils.unescapeHtml4(req.getParameter("txtIn"))
    val hdnTxt = StringEscapeUtils.unescapeHtml4(req.getParameter("hdnTxt"))
    println(optScriptFrom)
    println(optScriptTo)
    println(txtIn)
    println(hdnTxt)

    var txtOut = ""
    var lipi: IndicScript = null
    lipi = kannaDa
    if(optScriptFrom == "देवनागरी")
      txtOut = lipi.fromDevanagari(txtIn)
    else
      txtOut = lipi.toDevanagari(txtIn)

    resp.setContentType("text/plain")
    resp.setCharacterEncoding("UTF-8")
    resp.getWriter().println(txtOut)
    println(txtOut)
  }

}