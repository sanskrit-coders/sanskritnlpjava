package sanskritnlp.transliteration

object iast extends RomanScript {
  override val caseNeutral = true
  override val distinctCharacters = List("ṇ", "ṃ", "ś", "ñ", "u1", "ṣ", "ḥ", "ṭ", "ī", "ṝ", "ḍ", "ḷ", "ḹ", "ṛ", "ā", "ṅ")
  override val romanToDevaIndependentVowels = Map(
    "a" -> "अ", "ā" -> "आ",  "i" -> "इ", "ī" -> "ई",
    "u" -> "उ", "ū" -> "ऊ",
    "ṛ" -> "ऋ", "ṝ" -> "ॠ", "ḷ" -> "ऌ", "ḹ" -> "ॡ",
    "e" -> "ए",
    "ai" -> "ऐ",
    "o" -> "ओ", "au" -> "औ")

  override val romanToDevaDependentVowels = romanToDevaIndependentVowels.mapValues(devaIndependentToDependent(_)).filterKeys(_ != "a")

  override val romanToDevaConsonants = Map(
    "h" -> "ह्", "y" -> "य्", "v" -> "व्", "r" -> "र्", "l" -> "ल्",
    "ñ" -> "ञ्",
    "ṅ" -> "ङ्",
    "m" -> "म्",
    "ṇ" -> "ण्",
    "n" -> "न्",
    "jh" -> "झ्", "bh" -> "भ्",
    "gh" -> "घ्", "ḍh" -> "ढ्", "dh" -> "ध्",
    "j" -> "ज्", "b" -> "ब्", "g" -> "ग्",
    "ḍ" -> "ड्", "d" -> "द्",
    "kh" -> "ख्",
    "ph" -> "फ्", "ch" -> "छ्", "ṭh" -> "ठ्",
    "th" -> "थ्", "c" -> "च्", "ṭ" -> "ट्", "t" -> "त्",
    "k" -> "क्", "p" -> "प्",
    "ś" -> "श्", "ṣ" -> "ष्", "s" -> "स्")
  override val romanToDevaConsonantsNoVirama = romanToDevaConsonants.mapValues(_.replaceAll("(.+)्$", "$1"))
  override val romanToDevaContextFreeReplacements = Map(
    "ṃ" -> "ं",  "ḥ" -> "ः",
    "`" -> "ऽ", "." -> "।",
    "0" -> "०", "1"-> "१", "2"-> "२",
    "3"-> "३", "4"-> "४", "5"-> "५",
    "6"-> "६", "7"-> "७", "8"-> "८", "9"-> "९", "aum" -> "ॐ")

  override val devaDependentVowelsToRoman = romanToDevaDependentVowels.map(_.swap)
  override val devaIndependentVowelsToRoman = romanToDevaIndependentVowels.map(_.swap)
  override val aToRoman = devaIndependentVowelsToRoman("अ")
  override val devaConsonantsNoViramaToRomanVirama = romanToDevaConsonantsNoVirama.map(_.swap)
  override val devaConsonantsNoViramaToRoman = devaConsonantsNoViramaToRomanVirama.mapValues(_ + aToRoman)
  override val devaConsonantsToRoman = romanToDevaConsonants.map(_.swap)
  override val devaToRomanGeneral = romanToDevaContextFreeReplacements.map(_.swap) ++ Map("ऽ" -> "")

  def test_toDevanagari(): Unit = {
    val romanText = "Asaya auṣadhiḥ granthaḥ! l2kAro`sti. nāsti les4o`pi saṃśayaḥ. Kaaṣṭhaḥ bhoḥ. 12345" +
      "Aṃkuśeśvaram. iḍā"
    println("IAST Tests.")
    test_toDevanagari(romanText)
  }

}

object iastTest {
  def main(args: Array[String]): Unit = {
    iast.test_toDevanagari()
    iast.test_fromDevanagari()
  }
}

