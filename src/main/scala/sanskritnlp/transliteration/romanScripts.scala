package sanskritnlp.transliteration

trait RomanScript {
  val romanToDevaIndependentVowels: Map[String, String] = null

  val romanToDevaDependentVowels: Map[String, String] = null
  val romanToDevaConsonants: Map[String, String] = null

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

  def test_replaceRomanIndependentVowels(): Unit = {
    val hkText = "asaya auShadhiH granthaH! lRRkAro.asti. nAsti lesho.api saMshayaH."

    println("Test string : " + hkText)
    println("Result : " + replaceRomanIndependentVowels(hkText))
  }

  def replaceRomanKeys(str_in: String, romanToDevaMap: Map[String, String]): String = {
    val regexKeys = ("(" + romanToDevaMap.keys.mkString("|") + ")").r
    var output = str_in
    output = regexKeys.replaceAllIn(output, _ match { case regexKeys(key) => romanToDevaMap(key) })
    output
  }

  // Assumption: This should only be called after replaceRomanIndependentVowels .
  def replaceRomanDependentVowels(str_in: String): String = {
    var output = str_in
    val keyLengths = romanToDevaDependentVowels.keys.map(_.length).toList.distinct.sorted.reverse
    // The above yields List(3, 2, 1) for HK.

    keyLengths.foreach(x => {
      val vowelMap = romanToDevaDependentVowels.filter(t => (t._1.length() == x))
      output = replaceRomanKeys(output, vowelMap)
    })
    output
  }

  def test_replaceRomanDependentVowels(): Unit = {
    val hkText = "asaya auShadhiH granthaH! lRRkAro.asti. nAsti lesho.api saMshayaH."

    println("Test string : " + hkText)
    println("Result : " + replaceRomanDependentVowels(replaceRomanIndependentVowels(hkText)))
  }

  // Assumption: This should only be called after replaceRomanDependentVowels .
  def replaceConsonantsFollowedByVowels(str_in: String): String = {
    var output = str_in
    val keyLengths = romanToDevaDependentVowels.keys.map(_.length).toList.distinct.sorted.reverse
    // The above yields List(3, 2, 1) for HK.

    keyLengths.foreach(x => {
      val vowelMap = romanToDevaDependentVowels.filter(t => (t._1.length() == x))
      output = replaceRomanKeys(output, vowelMap)
    })
    output
  }

  def toDevanagari(str_in: String): String = {
    var output = replaceRomanIndependentVowels(str_in)
    output
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

  )
}

object romanScriptTest {
  def main(args: Array[String]): Unit = {
    harvardKyoto.test_replaceRomanIndependentVowels()
    harvardKyoto.test_replaceRomanDependentVowels()
  }
}
