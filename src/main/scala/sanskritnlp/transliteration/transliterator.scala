package sanskritnlp.transliteration

/**
  * General transliteration utilities.
  * Created by vvasuki on 2/21/16.
  */
object transliterator {

  def scriptFromString(schemeName: String) : Option[RomanScript] = {
    schemeName match {
      case "hk" => {
        return Some(harvardKyoto)
      }
      case "iast" => {
        return Some(iast)
      }
      case "as" => {
        return Some(as)
      }
      case "slp" => {
        return Some(slp)
      }
      case "optitrans" => {
        return Some(optitrans)
      }
      case _ => {return None}
    }
  }

  def transliterate(in_str: String, sourceScheme: String, destScheme: String): String = {
    // println("input string: " + in_str)
    var schemeOpt = scriptFromString(sourceScheme)
    if (schemeOpt.isDefined) {
      val return_opt = schemeOpt.get.toDevanagari(in_str)
      // println("return_opt: " + return_opt)
      if (return_opt.isEmpty) {
        throw new IllegalArgumentException("Could not transliterate " + in_str)
        return in_str
      } else {
        return return_opt.get
      }
    } else {
      schemeOpt = scriptFromString(destScheme)
      if (schemeOpt.isDefined) {
        return schemeOpt.get.fromDevanagari(in_str)
      } else {
        throw new IllegalArgumentException("Could not transliterate " + in_str)
      }
    }
  }
}
