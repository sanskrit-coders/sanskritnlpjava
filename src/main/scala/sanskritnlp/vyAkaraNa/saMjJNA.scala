package sanskritnlp.vyAkaraNa
import scala.collection.mutable.HashMap
import scala.collection.mutable.ArrayBuffer

object shivasUtra {
  var sUtra = "अइउण् ऋऌक् एओङ् ऐऔच् हयवरट् लँण् ञमङणनम् झभञ् घढधष् जबगडदश् खफछठथचटतव् कपय् शषसर् हल्"
  val sUtra_groups = sUtra.split(" ").toIndexedSeq

  // Letters representing indices, aka halitaH markers, anubandha
  // Eg: ण् क् ङ्. Note ण् is repeated.
  var halitaH = sUtra_groups.map(x => x.takeRight(2).toString)

  // Eg: उ ऌ ओ. There is no ambiguity with this.
  val gaNAntimAkShara = sUtra_groups.map(x => {
    if(x == "लँण्") "ल"
    else x.takeRight(3).head.toString
  })

  val itaH = halitaH ++ List("अँ")
  
  var sUtra_symbols = sUtra.replace(" ", "")
  // तस्य  लोपः॥ - तस्य इतो लोपः।
  itaH.foreach(y => {
    var x = y;
    if(x.last ==  'ँ') x = x.last.toString;
    sUtra_symbols = sUtra_symbols.replace(x, "")
    })

  val itsaMkhyA = gaNAntimAkShara.map(x => sUtra_symbols.lastIndexOf(x)) ++ List(sUtra_symbols.indexOf("ल"))
  val saMkhyA_map = (itaH zip itsaMkhyA).toMap

//   Example Input: pratyAhAra(start = "इ", end = "क्")
// Aka pratyAhAra
// Confidence in correctness: Low
// Reason: Well tested. Doesn't yet return all vowel forms.
  def get_symbols(start: String, endIn: String, bShortestMatch: Boolean = true) = {
    println(start + endIn)
    val beginPos = sUtra_symbols.indexOf(start)
    val end  = if(endIn == "ँ") "अँ" else endIn
    var endPos = saMkhyA_map(end)+1
    if(end == "ण्" && bShortestMatch)
      endPos = sUtra_symbols.indexOf("उ") + 1

    val akSharas = sUtra_symbols.substring(beginPos, endPos)
    
    akSharas
  }

  // आदिरन्त्येन सहेता॥
  def pratyAhAra(start: String, end: String, bShortestMatch: Boolean): IndexedSeq[String] = {
    
    val symbols = get_symbols(start, end, bShortestMatch)
    // हकारादिष्वकार उच्चारणार्थः॥
    val groups = symbols.groupBy(x => consonants.vyanjana_symbols contains x)
    println(groups)
    var akSharAH = ArrayBuffer[String]()
    if(groups.contains(true))
      akSharAH ++= groups(true).map(x => x.toString + vowels.virAma)
    if(groups.contains(false))
      akSharAH ++= groups(false).map(x => vowels.getAllForms(x.toString)).flatten
    akSharAH
  }

  def pratyAhAra(strIn: String, bShortestMatch: Boolean = true): IndexedSeq[String] = pratyAhAra(strIn(0).toString, strIn.substring(1), bShortestMatch)
  
  def test = {
    println("sUtra_symbols" + sUtra_symbols)
    println(gaNAntimAkShara zip itsaMkhyA)
    println("halitaH " + halitaH.mkString(" "))
    println("sUtra_symbols " + sUtra_symbols.mkString(" "))
    println("saMkhyA_map " + saMkhyA_map)
    
    println("pratyAhAra अण् " + akSharasaMjJNA.fetch("अण्").mkString(" "))
    println("pratyAhAra इक् " + akSharasaMjJNA.fetch("इक्").mkString(" "))
    println("pratyAhAra हल् " + akSharasaMjJNA.fetch("हल्").mkString(" "))
    println("pratyAhAra रँ " + akSharasaMjJNA.fetch("रँ").mkString(" "))
  }
}

object akSharasaMjJNA {
  val map: HashMap[String, IndexedSeq[String]] = HashMap(
    // 'eShAm antyaa itaH'
    // लण्मध्ये त्वित्सञ्ज्ञकः॥
    // Their use as markers: हलन्त्यं॥ with अनुवृत्ति - उपदेशे हल्-अन्त्यं इत् स्यात्।
    "इत्" -> (shivasUtra.itaH),
    
    "हल्" -> shivasUtra.pratyAhAra("हर्"),
    
    "आत्" -> IndexedSeq("आ")
  )

//   वृद्धिरादैच्॥
  map += "वृद्धि" -> (map("आत्") ++ shivasUtra.pratyAhAra("ऐच्"))
  
  def fetch(strIn: String, bShortestMatch: Boolean = true) = {
    // अणादिसञ्ज्ञार्थानि।
    map.getOrElse(strIn, shivasUtra.pratyAhAra(strIn, bShortestMatch))
  }
}