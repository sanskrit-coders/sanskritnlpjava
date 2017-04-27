package sanskritnlp.vyAkaraNa

import org.slf4j.LoggerFactory

class Syllable (strIn: String){
  var svara = ""
  var vyanjana = ""
  // TODO: deduce svara and vyanjana from strIn
  override def toString = vyanjana + svara
}

object symbols{
  val daNDa = Set('।', '॥')
  val abbreviation = '॰'

  val nukTa = '़'

  val OM = 'ॐ'

  

  object toneMarkers{
    val verticalBar = '॑'

    val doubleBar = '\u1CDA'

    val anudAtta = '॒'

    val grave = '॓'

    val acute = '॔'

  }

  val rupyaka = '₹'
}


object devanAgarI{
  val saMkhyA = Set('०', '१', '२', '३', '४', '५', '६', '७', '८', '९')
  val allSymbols = saMkhyA ++ consonants.vyanjana_symbols ++ vowels.svarAH ++ vowels.mAtrA ++ Set(vowels.virAma, vowels.avagraha,  vowels.anusvAra, vowels.visarga, vowels.chandrabindu, symbols.toneMarkers.verticalBar, symbols.toneMarkers.anudAtta)
  def isEncoding(str_in: String): Boolean = allSymbols.map(str_in.contains(_)).contains(true)
}

object consonants{
  val vyanjana_symbols = shivasUtra.get_symbols(start = "ह", endIn = "र्") ++ List('ळ')
  val vyanjanAni = vyanjana_symbols map (_.toString + vowels.virAma)
}

object vowels {
  // Symbols are assumed to occur in the following order:
  // (sthAna mAtrA tone nasalization).
  val log = LoggerFactory.getLogger(this.getClass)

  val hrasva = shivasUtra.get_symbols("अ", "क्") ++ List("ऎ", "ऒ")

  val hrasva2pluta = hrasva.map(x => (x, x match {
    case "ऌ" => "ॡ"
    case _ => x+"३"
  })).toMap

  val dIrgha = "आईऊॠ".map(_.toString) ++ shivasUtra.get_symbols("ए", "च्")

  val dIrgha2hrasva = (dIrgha zip hrasva.filterNot(_ == "ऌ")).toMap

  val hrasva2dIrgha = (hrasva.filterNot(_ == "ऌ") zip dIrgha).toMap

  val dIrgha2pluta = (dIrgha zip dIrgha.map(x => x.toString match {
    case "ऐ" | "औ" => x + "३"
    case _ => hrasva2pluta(dIrgha2hrasva(x))
  })).toMap

  val pluta = hrasva2pluta.values //++ List("ऐ३", "औ३")

  val svarAH = hrasva ++ dIrgha  ++ pluta

  val hrasvamAtrA = "िुृॢॆॊ".toIndexedSeq

  val dIrghamAtrA = "ाीूॄेोैौ".toIndexedSeq

  val plutamAtrA = IndexedSeq("ा३") ++ hrasvamAtrA.map(x => x match {
    case 'ऌ' => "ॡ"
    case _ => x+"३"
  }) //++ List("ै३", "ौ३")

  val mAtrA = hrasvamAtrA ++ dIrghamAtrA ++plutamAtrA

  val svara2mAtrA = (svarAH.drop(1) zip mAtrA).toMap

  val glottalStop = "ॽ"

  val mAtrA2svara = (mAtrA zip svarAH.drop(1)).toMap

  val virAma = "्"

  val avagraha = "ऽ"

  val anusvAra = "ं"

  val visarga = "ः"

  val chandrabindu = "ँ"

  /// TODO: Incomplete
  def getAllForms(base: String) = {
    var forms = IndexedSeq(base)
    if(hrasva contains base) {
      println("asdf "+IndexedSeq(hrasva2dIrgha(base), hrasva2pluta(base)))
    }
/*    else if(dIrgha contains base) {
      forms ++= IndexedSeq(dIrgha2hrasva(base))
      , dIrgha2pluta(base)
    }*/
    forms
  }

  object tone {
    val svarita = symbols.toneMarkers.verticalBar
    val anudAtta = symbols.toneMarkers.anudAtta
  }


  def test = {
    log info("svarAH " + svarAH.mkString(" "))
    log info("hrasva " + hrasva.mkString(" "))
    log info("dIrgha " + dIrgha.mkString(" "))
    log info("mAtrA " + mAtrA.mkString(" "))
    log info(hrasvamAtrA.size + " " + dIrghamAtrA.size)
    log info(svarAH.drop(1).mkString(" "))
    log info(mAtrA.mkString("_"))
    log info("svara2mAtrA " + svara2mAtrA)
    log info("mAtrA2svara " + mAtrA2svara)
    log info("dIrgha2hrasva " + dIrgha2hrasva)
    log info("hrasva2dIrgha " + dIrgha2hrasva)
    log info("vyanjanAni " + consonants.vyanjanAni.mkString(" "))
 }
}