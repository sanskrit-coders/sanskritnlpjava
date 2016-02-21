import scala.util.matching.Regex
import scala.util.matching.Regex.Match

val VIRAMA = "्"
// val consonantNonVowelPattern = s"(म|त|य)([^$VIRAMA])".r
val consonantNonVowelPattern = s"(थ|ठ|छ|स|ब|घ|ण|ट|ज|ग|न|ष|भ|ळ|ढ|ख|श|प|ह|ध|ङ|म|झ|ड|ल|व|र|फ|क|द|च|ञ|त|य)(?=[^$VIRAMA])".r
var output = "असय रामः "
output = consonantNonVowelPattern.replaceAllIn(output, (m:Match) => {m.group(0) + VIRAMA})
println("After virAma addition: " + output.mkString("-"))

import sanskritnlp.transliteration._
println(optitrans.toDevanagari("hello"))


