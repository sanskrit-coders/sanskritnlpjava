package sanskritnlp.transliteration

// Partial implementation of a variant of https://sites.google.com/site/sanskritcode/optitrans
object optitrans extends RomanScript {
  override val romanToDevaIndependentVowels = Map(
    "a" -> "अ", "A" -> "आ",  "aa" -> "आ",
    "i" -> "इ", "I" -> "ई",  "ii" -> "ई",
    "u" -> "उ", "U" -> "ऊ", "uu" -> "ऊ",
    "R" -> "ऋ", "RR" -> "ॠ",
    "LLi" -> "ऌ", "LLI" -> "ॡ",
    "e" -> "ए",
    "ai" -> "ऐ",
    "o" -> "ओ", "au" -> "औ")

  override val romanToDevaDependentVowels = romanToDevaIndependentVowels.mapValues(devaIndependentToDependent(_)).filterKeys(_ != "a")

  override val romanToDevaConsonants = Map(
    "h" -> "ह्", "y" -> "य्", "v" -> "व्", "r" -> "र्", "l" -> "ल्",
    "NJ" -> "ञ्",
    "NG" -> "ङ्",
    "m" -> "म्",
    "N" -> "ण्",
    "n" -> "न्",
    "jh" -> "झ्", "J" -> "झ्", "bh" -> "भ्", "B" -> "भ्",
    "gh" -> "घ्", "G" -> "घ्", "Dh" -> "ढ्", "dh" -> "ध्",
    "j" -> "ज्", "b" -> "ब्", "g" -> "ग्",
    "D" -> "ड्", "d" -> "द्",
    "kh" -> "ख्", "K" -> "ख्",
    "ph" -> "फ्", "P" -> "फ्", "Ch" -> "छ्", "C" -> "छ्", "Th" -> "ठ्",
    "th" -> "थ्", "c" -> "च्", "ch" -> "च्", "T" -> "ट्", "t" -> "त्",
    "k" -> "क्", "p" -> "प्",
    "sh" -> "श्", "S" -> "ष्", "Sh" -> "ष्", "s" -> "स्",
    // "L" -> "ळ्", // Removed to prevent erroneous output.
    "x" -> "क्ष्",
    "nk" -> "ङ्क्", "nK" -> "ङ्ख््", "nkh" -> "ङ्ख््",
    "ng" -> "ङ्ग्", "nG" -> "ङ्ख््",  "ngh" -> "ङ्ख््",
    "nc" -> "ञ्च्", "nC" -> "ञ्छ्", "nc" -> "ञ्च्", "nCh" -> "ञ्छ्",
    "nj" -> "ञ्ज्", "nJ" -> "ञ्झ्", "njh" -> "ञ्झ्"
  )
  override val romanToDevaConsonantsNoVirama = romanToDevaConsonants.mapValues(_.replaceAll("(.+)्$", "$1"))
  override val romanToDevaContextFreeReplacements = Map(
    "M" -> "ं",  "H" -> "ः",
    "." -> "।", ".." -> "॥", "|" -> "।",  ".a" -> "ऽ",
    "0" -> "०", "1"-> "१", "2"-> "२",
    "3"-> "३", "4"-> "४", "5"-> "५",
    "6"-> "६", "7"-> "७", "8"-> "८", "9"-> "९", "OM" -> "ॐ")

  override val devaDependentVowelsToRoman = romanToDevaDependentVowels.map(_.swap)
  override val devaIndependentVowelsToRoman = romanToDevaIndependentVowels.map(_.swap)
  override val aToRoman = devaIndependentVowelsToRoman("अ")
  override val devaConsonantsNoViramaToRomanVirama = romanToDevaConsonantsNoVirama.map(_.swap)
  override val devaConsonantsNoViramaToRoman = devaConsonantsNoViramaToRomanVirama.mapValues(_ + aToRoman)
  override val devaConsonantsToRoman = romanToDevaConsonants.map(_.swap)
  override val devaToRomanGeneral = romanToDevaContextFreeReplacements.map(_.swap)


  def test_toDevanagari(): Unit = {
    val text = "asaya auSadhiH auShadhiH granthaH! LLIkAro.asti. nAsti lesho.api saMshayaH. kaSThaM bhoH. shankara! sanjIvaya. 12345"
    // Error output if L=ळ is enabled: असय औषधिः औषधिः ग्रन्थः! ळ्ळीकारोऽस्ति। नास्ति लेशोऽपि संशयः। कष्ठं भोः। शङ्कर! सञ्जीवय। १२३४५
    println("OPTITRANS Tests.")
    test_toDevanagari(text)
  }

}

object optitransTest {
  def main(args: Array[String]): Unit = {
    optitrans.test_toDevanagari()
    optitrans.test_fromDevanagari()
    optitrans.test_restoreEscapeSequences()
    optitrans.test_restoreRomanBetweenStrings()
  }
}
