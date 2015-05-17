package sanskritnlp.transliteration

object as extends RomanScript {
  override val caseNeutral = true
  override val romanToDevaIndependentVowels = Map(
    "a" -> "अ", "a1" -> "आ",  "i" -> "इ", "i1" -> "ई",
    "u" -> "उ", "u1" -> "ऊ",
    "r2" -> "ऋ", "r4" -> "ॠ", "l2" -> "ऌ", "l4" -> "ॡ",
    "e" -> "ए",
    "ai" -> "ऐ",
    "o" -> "ओ", "au" -> "औ")

  override val romanToDevaDependentVowels = romanToDevaIndependentVowels.mapValues(devaIndependentToDependent(_)).filterKeys(_ != "a")

  override val romanToDevaConsonants = Map(
    "h" -> "ह्", "y" -> "य्", "v" -> "व्", "r" -> "र्", "l" -> "ल्",
    "n5" -> "ञ्",
    "n3" -> "ङ्",
    "m" -> "म्",
    "n2" -> "ण्",
    "n" -> "न्",
    "jh" -> "झ्", "bh" -> "भ्",
    "gh" -> "घ्", "d2h" -> "ढ्", "dh" -> "ध्",
    "j" -> "ज्", "b" -> "ब्", "g" -> "ग्",
    "d2" -> "ड्", "d" -> "द्",
    "kh" -> "ख्",
    "ph" -> "फ्", "ch" -> "छ्", "t2h" -> "ठ्",
    "th" -> "थ्", "c" -> "च्", "t2" -> "ट्", "t" -> "त्",
    "k" -> "क्", "p" -> "प्",
    "s4" -> "श्", "s2" -> "ष्", "s" -> "स्")
  override val romanToDevaConsonantsNoVirama = romanToDevaConsonants.mapValues(_.replaceAll("्", ""))
  override val romanToDevaContextFreeReplacements = Map(
    "m2" -> "ं",  "h2" -> "ः",
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
    val romanText = "asaya auS2adhih2 granthah2! l2kAro`sti. na1sti les4o`pi sam2s4ayah2. Kas2t2ham2 bhoh2. 12345" +
        "Am2kus4es4varam. id2a1"
    println("AS Tests.")
    test_toDevanagari(romanText)
  }

}

object asTest {
  def main(args: Array[String]): Unit = {
    as.test_toDevanagari()
    as.test_fromDevanagari()
  }
}
