package sanskritnlp.transliteration

/**
 *

Much Sanskrit in the text appears in the European Indological form, which is
coded in pe.txt with the the AS (Anglicized Sanskrit) coding.

The general AS scheme, as described in CDSL.pdf, uses Latin alphabetical
letters 'x (a-z,A-Z), possibly with suffixed numbers; the letter-number
combinations are, in the general scheme:
x1 = macron
x2 = dot below
x3 = dot above
x4 = accent aigu
x5 = tilde
x6 = dash below
x7 = umlaut
x10 = circonflex (hat)
x11 = accent grave

Here are the characters that occur in pe.txt in this coding,
with their approximate frequency:

A1  7721 := Ā  (\u0100)  LATIN CAPITAL LETTER A WITH MACRON
a1 67706 := ā  (\u0101)  LATIN SMALL LETTER A WITH MACRON
d2  4974 := ḍ  (\u1e0d)  LATIN SMALL LETTER D WITH DOT BELOW
D2   380 := Ḍ  (\u1e0c)  LATIN CAPITAL LETTER D WITH DOT BELOW
h2   439 := ḥ  (\u1e25)  LATIN SMALL LETTER H WITH DOT BELOW
H2     9 := Ḥ  (\u1e24)  LATIN CAPITAL LETTER H WITH DOT BELOW
I1  1625 := Ī  (\u012a)  LATIN CAPITAL LETTER I WITH MACRON
i1 19497 := ī  (\u012b)  LATIN SMALL LETTER I WITH MACRON
l2     2 := ḷ  (\u1e37)  LATIN SMALL LETTER L WITH DOT BELOW
m2    13 := ṃ  (\u1e43)  LATIN SMALL LETTER M WITH DOT BELOW
M3   180 := Ṁ  (\u1e40)  LATIN CAPITAL LETTER M WITH DOT ABOVE
m3  2500 := ṁ  (\u1e41)  LATIN SMALL LETTER M WITH DOT ABOVE
N2  1010 := Ṇ  (\u1e46)  LATIN CAPITAL LETTER N WITH DOT BELOW
n2 20671 := ṇ  (\u1e47)  LATIN SMALL LETTER N WITH DOT BELOW
N3   356 := Ṅ  (\u1e44)  LATIN CAPITAL LETTER N WITH DOT ABOVE
n3  3161 := ṅ  (\u1e45)  LATIN SMALL LETTER N WITH DOT ABOVE
N5   197 := Ñ  (\u00d1)  LATIN CAPITAL LETTER N WITH TILDE
n5  2679 := ñ  (\u00f1)  LATIN SMALL LETTER N WITH TILDE
R2  1625 := Ṛ  (\u1e5a)  LATIN CAPITAL LETTER R WITH DOT BELOW
r2  6630 := ṛ  (\u1e5b)  LATIN SMALL LETTER R WITH DOT BELOW
S2  1027 := Ṣ  (\u1e62)  LATIN CAPITAL LETTER S WITH DOT BELOW
s2 17116 := ṣ  (\u1e63)  LATIN SMALL LETTER S WITH DOT BELOW
S4 12639 := Ś  (\u015a)  LATIN CAPITAL LETTER S WITH ACUTE
s4 11533 := ś  (\u015b)  LATIN SMALL LETTER S WITH ACUTE
T2   528 := Ṭ  (\u1e6c)  LATIN CAPITAL LETTER T WITH DOT BELOW
t2  5280 := ṭ  (\u1e6d)  LATIN SMALL LETTER T WITH DOT BELOW
U1   614 := Ū  (\u016a)  LATIN CAPITAL LETTER U WITH MACRON
u1  4898 := ū  (\u016b)  LATIN SMALL LETTER U WITH MACRON
 */

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
  override val romanToDevaConsonantsNoVirama = romanToDevaConsonants.mapValues(_.replaceAll("(.+)्$", "$1"))
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

  override val distinctCharacters = (romanToDevaIndependentVowels ++ romanToDevaConsonants ++ romanToDevaContextFreeReplacements) .keys.filter(_.matches(""".+?\d""")).toList

  def test_toDevanagari(): Unit = {
    val romanText = "asaya auS2adhih2 granthah2! l2kAro`sti. na1sti les4o`pi sam2s4ayah2. Kas2t2ham2 bhoh2. 12345" +
        "Am2kus4es4varam. id2a1"
    println("AS Tests.")
    test_toDevanagari(romanText)
    test_toDevanagari(romanText.toUpperCase)
  }

}

object asTest {
  def main(args: Array[String]): Unit = {
    as.test_toDevanagari()
    as.test_fromDevanagari()
    println(as.distinctCharacters)
    println(as.distinctCharacters.toList.map(x => iast.fromDevanagari(as.toDevanagari(x).get)) )
  }
}
