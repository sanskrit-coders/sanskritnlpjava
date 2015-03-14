package sanskritnlp.transliteration

trait RomanScript {
  val map_to_devanAgarI_independent_vowels: Map[String, String] = null

  val map_to_devanAgarI_dependent_vowels: Map[String, String] = null

  def replaceIndependentVowels(str_in: String, vowel_length: Int): String = {
    val independent_vowels = map_to_devanAgarI_independent_vowels.keys.filter(_.length() == vowel_length)
    val regex_independent_vowels = ("([^a-zA-Z])(" + independent_vowels.mkString("|") + ")").r
    var output = str_in
    output = regex_independent_vowels.replaceAllIn(output, _ match { case regex_independent_vowels(c1, key) => c1 + map_to_devanAgarI_independent_vowels(key) })
    val regex_independent_vowels_at_beginning = ("^(" + independent_vowels.mkString("|") + ")").r
    output = regex_independent_vowels_at_beginning.replaceAllIn(output, _ match { case regex_independent_vowels_at_beginning(key) => map_to_devanAgarI_independent_vowels(key) })
    output
  }

  def replaceIndependentVowels(str_in: String): String = {
    var output = str_in
    output = replaceIndependentVowels(output, 3)
    output = replaceIndependentVowels(output, 2)
    output = replaceIndependentVowels(output, 1)
    output
  }

  def test_replaceIndependentVowels(): Unit = {
    val hkText = "asaya auShadhiH granthaH! lRRkAro.asti. nAsti lesho.api saMshayaH."

    println("Test string : " + hkText)
    println("Result : " + replaceIndependentVowels(hkText))
  }

  def replaceDependentVowels(str_in: String): String = {
    var output = str_in
  }

  def toDevanagari(str_in: String): String = {
    var output = replaceIndependentVowels(str_in)
    output
  }
}

object harvardKyoto extends RomanScript {
	override val map_to_devanAgarI_independent_vowels = Map(
  "a" -> "अ", "A" -> "आ",  "i" -> "इ", "I" -> "ई",
  "u" -> "उ", "U" -> "ऊ",
  "R" -> "ऋ", "RR" -> "ॠ", "lR" -> "ऌ", "lRR" -> "ॡ", 
  "e" -> "ए",
  "ai" -> "ऐ",
  "o" -> "ओ", "au" -> "औ")
  
  override val map_to_devanAgarI_dependent_vowels = Map(
  "A" -> "ा",
  "i" -> "ि", 
  "I" -> "ी",
  "u" -> "ु", "U" -> "ू",
  "R" -> "ृ", "RR" -> "ॄ",
  "lR" -> "ॢ", "lRR" -> "ॣ",
  "e" -> "े",
  "ai" ->  "ै",
  "o" -> "ो",  "au" -> "ौ")
}

object romanScriptTest {
  def main(args: Array[String]): Unit = {
    harvardKyoto.test_replaceIndependentVowels()
  }
}
