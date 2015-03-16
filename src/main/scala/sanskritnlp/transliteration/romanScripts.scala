package sanskritnlp.transliteration

// Point of entry: toDevanagari()
// Read that function, and the logic will be clear.

trait RomanScript {
  val romanToDevaIndependentVowels: Map[String, String] = null

  val romanToDevaDependentVowels: Map[String, String] = null
  val romanToDevaConsonants: Map[String, String] = null
  val romanToDevaContextFreeReplacements: Map[String, String] = null

  def replaceRomanIndependentVowels(str_in: String, vowelMap: Map[String, String]): String = {
    val regex_independent_vowels = ("([^a-zA-Z])(" + vowelMap.keys.mkString("|") + ")").r
    var output = str_in
    output = regex_independent_vowels.replaceAllIn(output, _ match { case regex_independent_vowels(c1, key) => c1 + vowelMap(key) })
    val regex_independent_vowels_at_beginning = ("^(" + vowelMap.keys.mkString("|") + ")").r
    output = regex_independent_vowels_at_beginning.replaceAllIn(output, _ match { case regex_independent_vowels_at_beginning(key) => vowelMap(key) })
    output
  }

  def replaceRomanIndependentVowels(str_in: String): String = {
    var output = str_in
    val keyLengths = romanToDevaIndependentVowels.keys.map(_.length).toList.distinct.sorted.reverse
    // The above yields List(3, 2, 1) for HK.

    keyLengths.foreach(x => {
      val vowelMap = romanToDevaIndependentVowels.filter(t => (t._1.length() == x))
      output = replaceRomanIndependentVowels(output, vowelMap)
    })
    output
  }

  def test_replaceRomanIndependentVowels(str_in: String): Unit = {
    println("Test string : " + str_in)
    println("Result : " + replaceRomanIndependentVowels(str_in))
  }

  def replaceRomanKeys(str_in: String, romanToDevaMap: Map[String, String]): String = {
    val keysWithoutWildcards = romanToDevaMap.keys.filterNot(_.contains("."))
    val keysWithWildCards = romanToDevaMap.keys.filter(_.contains("."))
    val regexKeys = ("(" + keysWithoutWildcards.mkString("|") + ")").r
    // println(regexKeys)
    var output = str_in
    output = regexKeys.replaceAllIn(output, _ match { case regexKeys(key) => romanToDevaMap(key) })
    keysWithWildCards.foreach(x => output = output.replaceAllLiterally(x, romanToDevaMap(x)))
    output
  }

  def replaceRomanKeysLongestFirst(str_in: String, romanToDevaMap: Map[String, String]): String = {
    var output = str_in
    val keyLengths = romanToDevaMap.keys.map(_.length).toList.distinct.sorted.reverse
    // The above yields List(3, 2, 1) for HK.

    keyLengths.foreach(x => {
      val mapping = romanToDevaMap.filter(t => (t._1.length() == x))
      // println(mapping)
      output = replaceRomanKeys(output, mapping)
    })
    output
  }

  def test_replaceRomanDependentVowels(str_in: String): Unit = {
    println("Test string : " + str_in)
    println("Result : " + replaceRomanKeysLongestFirst(replaceRomanIndependentVowels(str_in, romanToDevaDependentVowels), romanToDevaDependentVowels))
  }

  def replaceRomanConsonantsFollowedByVowels(str_in: String, consonantMapNoVirama: Map[String, String]): String = {
    val regex_consonant_vowel = ("(" + consonantMapNoVirama.keys.mkString("|") +")(a|" + romanToDevaDependentVowels.values.mkString("|") + ")").r
    var output = str_in
    output = regex_consonant_vowel.replaceAllIn(output, _ match { case regex_consonant_vowel(consonant, vowel) => consonantMapNoVirama(consonant) + vowel.replaceAll("a", "") })
    output
  }

  // Assumption: This should only be called after replaceRomanDependentVowels .
  def replaceRomanConsonantsFollowedByVowels(str_in: String): String = {
    var output = str_in
    val romanToDevaConsonantsNoVirama = romanToDevaConsonants.mapValues(_.replaceAll("्", ""))
    val keyLengths = romanToDevaConsonantsNoVirama.keys.map(_.length).toList.distinct.sorted.reverse
    // The above yields List(3, 2, 1) for HK.
    // println(keyLengths)

    keyLengths.foreach(x => {
      val mapping = romanToDevaConsonantsNoVirama.filter(t => (t._1.length() == x))
      // println(mapping)
      output = replaceRomanConsonantsFollowedByVowels(output, mapping)
    })
    output
  }

  def test_replaceRomanConsonantsFollowedByVowels(str_in: String): Unit = {
    println("Test string : " + str_in)
    println("Result : " + replaceRomanConsonantsFollowedByVowels(replaceRomanKeysLongestFirst(replaceRomanIndependentVowels(str_in), romanToDevaDependentVowels)))
  }

  def toDevanagari(str_in: String): String = {
    var output = str_in
    output = replaceRomanIndependentVowels(output)
    output = replaceRomanKeysLongestFirst(output, romanToDevaDependentVowels)
    output = replaceRomanConsonantsFollowedByVowels(output)
    output = replaceRomanKeysLongestFirst(output, romanToDevaConsonants)
    output = replaceRomanKeysLongestFirst(output, romanToDevaContextFreeReplacements)
    output
  }

  def test_toDevanagari(str_in : String) = {
    test_replaceRomanIndependentVowels(str_in)
    test_replaceRomanDependentVowels(str_in)
    test_replaceRomanConsonantsFollowedByVowels(str_in)
    println(toDevanagari(str_in))
  }
}

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
  override val romanToDevaContextFreeReplacements = Map(
    "M" -> "ं",  "H" -> "ः",
    "'" -> "ऽ", "." -> "।",
    "0" -> "०", "1"-> "१", "2"-> "२",
    "3"-> "३", "4"-> "४", "5"-> "५",
    "6"-> "६", "7"-> "७", "8"-> "८", "9"-> "९")

  def test_toDevanagari(): Unit = {
    val hkText = "asaya auSadhiH granthaH! lRRkAro.asti. nAsti lezo.api saMzayaH. kaSThaM bhoH. 12345"
    println("HK Tests.")
    test_toDevanagari(hkText)
  }

}

object romanScriptTest {
  def main(args: Array[String]): Unit = {
    harvardKyoto.test_toDevanagari()
  }
}
