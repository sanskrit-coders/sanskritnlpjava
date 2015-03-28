package sanskritnlp.transliteration

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


  def replaceKeys(str_in: String, mapping: Map[String, String]): String = {
    val keysWithoutWildcards = mapping.keys.filterNot(_.contains("."))
    val keysWithWildCards = mapping.keys.filter(_.contains("."))
    val regexKeys = ("(" + keysWithoutWildcards.mkString("|") + ")").r
    // println(regexKeys)
    var output = str_in
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

  def test_replaceRomanDependentVowels(str_in: String): Unit = {
    println("Test string : " + str_in)
    println("Result : " + replaceKeysLongestFirst(replaceRomanIndependentVowels(str_in, romanToDevaDependentVowels), romanToDevaDependentVowels))
  }

  def replaceRomanConsonantsFollowedByVowels(str_in: String, consonantMapNoVirama: Map[String, String]): String = {
    val regex_consonant_vowel = ("(" + consonantMapNoVirama.keys.mkString("|") +")($aToRoman%s|" + romanToDevaDependentVowels.values.mkString("|") + ")").r
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
    println("Result : " + replaceRomanConsonantsFollowedByVowels(replaceKeysLongestFirst(replaceRomanIndependentVowels(str_in), romanToDevaDependentVowels)))
  }

  def toDevanagari(str_in: String): String = {
    var output = str_in
    output = replaceRomanIndependentVowels(output)
    output = replaceKeysLongestFirst(output, romanToDevaDependentVowels)
    output = replaceRomanConsonantsFollowedByVowels(output)
    output = replaceKeysLongestFirst(output, romanToDevaConsonants)
    output = replaceKeysLongestFirst(output, romanToDevaContextFreeReplacements)
    output
  }

  def replaceDevanagariConsonantsFollowedByVowels(str_in: String): String = {
    val pattern = ("(" + devaConsonantsNoViramaToRomanVirama.keys.mkString("|") + ")(" +
      devaDependentVowelsToRoman.keys.mkString("|") + ")").r
    println(devaConsonantsNoViramaToRomanVirama)
    var output = str_in
    output = pattern.replaceAllIn(output, _ match { case pattern(consonant, vowel) =>
      devaConsonantsNoViramaToRomanVirama(consonant) + devaDependentVowelsToRoman(vowel) })
    output
  }

  def fromDevanagari(str_in: String): String = {
    var output = str_in

    // Replace consonants followed by dependent vowel signs first.
    output = replaceDevanagariConsonantsFollowedByVowels(output)
    println(output)

    // Replace consonants followed by virAma next.
    output = replaceKeysLongestFirst(output, devaConsonantsToRoman)
    println(output)

    output = replaceKeysLongestFirst(output, devaConsonantsNoViramaToRoman)
    println(output)

    output = replaceKeysLongestFirst(output, devaIndependentVowelsToRoman)
    println(output)
    output = replaceKeysLongestFirst(output, devaToRomanGeneral)
    output
  }

  def test_toDevanagari(str_in : String) = {
    test_replaceRomanIndependentVowels(str_in)
    test_replaceRomanDependentVowels(str_in)
    test_replaceRomanConsonantsFollowedByVowels(str_in)
    println(toDevanagari(str_in))
  }

  def test_fromDevanagari(str_in : String = "असय औषधिः ग्रन्थः! ॡकारो।अस्ति। नास्ति लेशो।अपि संशयः। कष्ठं भोः। १२३४५") = {
    println("Input: " + str_in)
    println(fromDevanagari(str_in))
  }
}
