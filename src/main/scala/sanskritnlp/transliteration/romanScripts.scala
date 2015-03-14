package sanskritnlp.transliteration

trait RomanScript {
  val map_to_devanAgarI_independent_vowels: Map[String, String] = null

  val map_to_devanAgarI_dependent_vowels: Map[String, String] = null
  
  def toDevanagari(str: String) = {
  	val independent_vowels_codelength_3 = map_to_devanAgarI_independent_vowels.keys.filter(_.length() == 3)
  	val independent_vowels_codelength_2 = map_to_devanAgarI_independent_vowels.keys.filter(_.length() == 2)
  	val independent_vowels_codelength_1 = map_to_devanAgarI_independent_vowels.keys.filter(_.length() == 1)
  	// var output = str.replaceAllIn()
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
  	println(harvardKyoto)
  }
}
