package sanskritnlp.transliteration

import java.util.Collections

import scala.util.matching.Regex.Match

// Point of entry: toDevanagari()
// Read that function, and the logic will be clear.

trait RomanScript {
  val romanToDevaIndependentVowels: Map[String, String] = null

  val romanToDevaDependentVowels: Map[String, String] = null
  val romanToDevaConsonants: Map[String, String] = null
  val romanToDevaConsonantsNoVirama: Map[String, String] = null
  val romanToDevaContextFreeReplacements: Map[String, String] = null

  val devaConsonantsNoViramaToRomanVirama: Map[String, String] = null
  val devaConsonantsNoViramaToRoman: Map[String, String] = null
  val devaIndependentVowelsToRoman: Map[String, String] = null
  val devaDependentVowelsToRoman: Map[String, String] = null
  val devaConsonantsToRoman: Map[String, String] = null
  val aToRoman: String = null
  val devaToRomanGeneral: Map[String, String] = null
  val distinctCharacters: List[String] = null

  val caseNeutral = false

  val devaIndependentToDependent: Map[String, String] = Map(
    "अ" -> "",
    "आ" -> "ा",
    "इ" -> "ि",
    "ई" -> "ी",
    "उ" -> "ु", "ऊ" -> "ू",
    "ऋ" -> "ृ", "ॠ" -> "ॄ",
    "ऌ" -> "ॢ", "ॡ" -> "ॣ",
    "ए" -> "े",
    "ऐ" ->  "ै",
    "ओ" -> "ो",  "औ" -> "ौ")


  def replaceKeys(str_in: String, mapping: Map[String, String]): String = {
    def containsWildcard(x:String): Boolean = {x.contains(".") || x.contains("|")}

    val keysWithoutWildcards = mapping.keys.filterNot(x => containsWildcard(x))
    val keysWithWildCards = mapping.keys.filter(x => containsWildcard(x))
    val regexKeys = ("(" + keysWithoutWildcards.mkString("|") + ")").r
    // println(regexKeys)
    var output = str_in;
    output = regexKeys.replaceAllIn(output, _ match { case regexKeys(key) => mapping(key) })
    keysWithWildCards.foreach(x => output = output.replaceAllLiterally(x, mapping(x)))
    output
  }

  def replaceKeysLongestFirst(str_in: String, mapping: Map[String, String]): String = {
    var output = str_in
    val keyLengths = mapping.keys.map(_.length).toList.distinct.sorted.reverse
    // The above yields List(3, 2, 1) for HK.
    keyLengths.foreach(x => {
      val mapping_length_x = mapping.filter(t => (t._1.length() == x))
      // println(mapping)
      output = replaceKeys(output, mapping_length_x)
    })
    output
  }

  def replaceRomanDependentVowels(str_in: String, vowelMap: Map[String, String]): String = {
    val regex_dependent_vowels = ("(" + romanToDevaConsonantsNoVirama.keys.mkString("|") + ")" + "(" + vowelMap.keys.mkString("|") + ")").r
    var output = str_in
    output = regex_dependent_vowels.replaceAllIn(output, _ match { case regex_dependent_vowels(c1, key) => c1 + vowelMap(key) })
    output
  }

  def replaceRomanDependentVowels(str_in: String): String = {
    var output = str_in
    val keyLengths = romanToDevaDependentVowels.keys.map(_.length).toList.distinct.sorted.reverse
    // The above yields List(3, 2, 1) for HK.

    keyLengths.foreach(x => {
      val vowelMap = romanToDevaDependentVowels.filter(t => (t._1.length() == x))
      output = replaceRomanDependentVowels(output, vowelMap)
    })
    output
  }

  def test_replaceRomanDependentVowels(str_in: String): Unit = {
    println("Test string : " + str_in)
    println("Result : " + replaceRomanDependentVowels(str_in))
  }

  def replaceRomanConsonantsFollowedByVowels(str_in: String, consonantMapNoVirama: Map[String, String]): String = {
    val regex_consonant_vowel = ("(" + consonantMapNoVirama.keys.mkString("|") + ")"
      + s"($aToRoman|" + romanToDevaDependentVowels.values.mkString("|") + ")").r
    var output = str_in
    output = regex_consonant_vowel.replaceAllIn(output, _ match { case regex_consonant_vowel(consonant, vowel) => consonantMapNoVirama(consonant) + vowel.replaceAll("a", "") })
    output
  }

  // Assumption: This should only be called after replaceRomanDependentVowels .
  def replaceRomanConsonantsFollowedByVowels(str_in: String): String = {
    var output = str_in
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
    println("Result : " + replaceRomanConsonantsFollowedByVowels(replaceKeysLongestFirst(replaceRomanDependentVowels(str_in), romanToDevaDependentVowels)))
  }

  def toDevanagari(str_in: String): String = {
    var output = str_in
    if (caseNeutral) {
      output = output.toLowerCase
    }
    output = replaceRomanDependentVowels(output)
    output = replaceRomanConsonantsFollowedByVowels(output)
    output = replaceKeysLongestFirst(output, romanToDevaConsonants ++ romanToDevaContextFreeReplacements ++ romanToDevaIndependentVowels)
    output
  }

  def replaceDevanagariConsonants(str_in: String): String = {
    var output = str_in

    // First add a few virAma-s.
    val VIRAMA = "्"
    val consonantVowelPattern = (
      "(" + devaConsonantsNoViramaToRomanVirama.keys.mkString("|") + ")"
      + "(" + devaDependentVowelsToRoman.keys.mkString("|") + ")").r
    output = consonantVowelPattern.replaceAllIn(output, _ match { case consonantVowelPattern(consonant, vowel) =>
      consonant + VIRAMA + vowel})
    val consonantNonVowelPattern = (
      "(" + devaConsonantsNoViramaToRomanVirama.keys.mkString("|") + ")"
        + s"(?=[^$VIRAMA" + devaDependentVowelsToRoman.keys.mkString("") + "])").r
    // println(consonantNonVowelPattern)
    output = consonantNonVowelPattern.replaceAllIn(output, (m:Match) => {m.group(0) + VIRAMA + aToRoman})
    if(romanToDevaConsonantsNoVirama.values.toList.contains(output.last.toString)) {
      output = output + VIRAMA + aToRoman
    }
    // println("After virAma addition: " + output.mkString("-"))
    output = replaceKeysLongestFirst(output, devaConsonantsToRoman)
    output
  }

  def fromDevanagari(str_in: String): String = {
    var output = str_in

    output = replaceDevanagariConsonants(output)
    // println("Consonant replacement: " + output)

    output = replaceKeysLongestFirst(output, devaDependentVowelsToRoman)
    // println(output)
    output = replaceKeysLongestFirst(output, devaIndependentVowelsToRoman)
    // println(output)
    output = replaceKeysLongestFirst(output, devaToRomanGeneral)
    output
  }

  def restoreEscapeSequences(str_in: String): String = {
    var output = str_in
    val escapePattern = """\\(.्?)""".r
    output = escapePattern.replaceAllIn(output, _ match { case escapePattern(matched) => """\\""" + fromDevanagari(matched) })
    output
  }
  def test_restoreEscapeSequences() = {
    val str1 = """हरिः ॐ १ 1ad\न् \त्"""
    println(restoreEscapeSequences(str1))
  }

  // ASSUMPTION: Escape characters appropriately in romanStart and romanEnd.
  def restoreRomanBetweenStrings(str_in: String, romanStart: String, romanEnd: String): String = {
    var output = str_in
    val escapePattern = (romanStart + """(.+?)""" + romanEnd).r
    output = escapePattern.replaceAllIn(output, _ match { case escapePattern(matched) => romanStart + fromDevanagari(matched) + romanEnd})
    output
  }
  def test_restoreRomanBetweenStrings() = {
    val str1 = """हरिः ॐ १ {#Pअगे#} 1ad {#आन्द्#} \न् \त्"""
    println(restoreRomanBetweenStrings(str1, "\\{#", "#\\}"))
  }

  def test_toDevanagari(str_in : String) = {
    test_replaceRomanDependentVowels(str_in)
    test_replaceRomanConsonantsFollowedByVowels(str_in)
    println(toDevanagari(str_in))
  }

  def test_fromDevanagari(str_in : String = "असय औषधिः ग्रन्थः! ॡकारो।ऽस्ति। नास्ति लेशोऽपि संशयः। कीलकम्? कूपिः?  कष्ठं भोः। शङकर! सञजीवय। १२३४५.. ॐ तत्।") = {
    println("Input: " + str_in)
    println("Output: " + fromDevanagari(str_in))
  }
}
