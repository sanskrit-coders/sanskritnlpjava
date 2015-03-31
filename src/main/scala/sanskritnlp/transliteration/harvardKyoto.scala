package sanskritnlp.transliteration

object harvardKyoto extends RomanScript {
  override val romanToDevaIndependentVowels = Map(
    "a" -> "अ", "A" -> "आ",  "i" -> "इ", "I" -> "ई",
    "u" -> "उ", "U" -> "ऊ",
    "R" -> "ऋ", "RR" -> "ॠ", "lR" -> "ऌ", "lRR" -> "ॡ",
    "e" -> "ए",
    "ai" -> "ऐ",
    "o" -> "ओ", "au" -> "औ")

  override val romanToDevaDependentVowels = Map(
    "A" -> "ा",
    "i" -> "ि",
    "I" -> "ी",
    "u" -> "ु", "U" -> "ू",
    "R" -> "ृ", "RR" -> "ॄ",
    "lR" -> "ॢ", "lRR" -> "ॣ",
    "e" -> "े",
    "ai" ->  "ै",
    "o" -> "ो",  "au" -> "ौ")

  override val romanToDevaConsonants = Map(
    "h" -> "ह्", "y" -> "य्", "v" -> "व्", "r" -> "र्", "l" -> "ल्",
    "J" -> "ञ्",
    "G" -> "ङ्",
    "m" -> "म्",
    "N" -> "ण्",
    "n" -> "न्",
    "jh" -> "झ्", "bh" -> "भ्",
    "gh" -> "घ्", "Dh" -> "ढ्", "dh" -> "ध्",
    "j" -> "ज्", "b" -> "ब्", "g" -> "ग्",
    "D" -> "ड्", "d" -> "द्",
    "kh" -> "ख्",
    "ph" -> "फ्", "ch" -> "छ्", "Th" -> "ठ्",
    "th" -> "थ्", "c" -> "च्", "T" -> "ट्", "t" -> "त्",
    "k" -> "क्", "p" -> "प्",
    "z" -> "श्", "S" -> "ष्", "s" -> "स्",
    "L" -> "ळ्")
  override val romanToDevaConsonantsNoVirama = romanToDevaConsonants.mapValues(_.replaceAll("्", ""))
  override val romanToDevaContextFreeReplacements = Map(
    "M" -> "ं",  "H" -> "ः",
    "'" -> "ऽ", "." -> "।",
    "0" -> "०", "1"-> "१", "2"-> "२",
    "3"-> "३", "4"-> "४", "5"-> "५",
    "6"-> "६", "7"-> "७", "8"-> "८", "9"-> "९", "OM" -> "ॐ")

  override val devaDependentVowelsToRoman = romanToDevaDependentVowels.map(_.swap)
  override val devaIndependentVowelsToRoman = romanToDevaIndependentVowels.map(_.swap)
  override val aToRoman = devaIndependentVowelsToRoman("अ")
  override val devaConsonantsNoViramaToRomanVirama = romanToDevaConsonantsNoVirama.map(_.swap)
  override val devaConsonantsNoViramaToRoman = devaConsonantsNoViramaToRomanVirama.mapValues(_ + aToRoman)
  override val devaConsonantsToRoman = romanToDevaConsonants.map(_.swap)
  override val devaToRomanGeneral = romanToDevaContextFreeReplacements.map(_.swap) ++ Map("ऽ" -> "")

  def test_toDevanagari(): Unit = {
    val hkText = "asaya auSadhiH granthaH! lRRkAro.asti. nAsti lezo.api saMzayaH. kaSThaM bhoH. 12345"
    println("HK Tests.")
    test_toDevanagari(hkText)
  }

}

object harvardKyotoTest {
  def main(args: Array[String]): Unit = {
    harvardKyoto.test_toDevanagari()
    harvardKyoto.test_fromDevanagari()
  }
}
