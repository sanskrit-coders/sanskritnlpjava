package sanskritnlp.transliteration
import sanskritnlp.vyAkaraNa._

trait IndicScript {
  val mapFromDevanagari: Map[Char, Char] = null

  val mapToDevanagari: Map[Char, Char] = null

  def fromDevanagari(str: String): String = str.map(x => mapFromDevanagari.getOrElse(x, x)).mkString("")

  def toDevanagari(str: String) = str.map(x => mapToDevanagari.getOrElse(x, x)).mkString("")

  def test = {
    val devanAgarI_str = devanAgarI.allSymbols.mkString("-")
    println(devanAgarI_str)
    println(fromDevanagari(devanAgarI_str))
  }
}

object kannaDa extends IndicScript{
  // Unicode chars copied from kn-itrans.mim
  // http://www.koders.com/noncode/fid696F9AB94B6D1DB0A554AFA7D5E5C07F132E6CF9.aspx
  // Links for other languages *-itrans.mim are also found there.

  override val mapFromDevanagari = Map(
  'अ' -> 'ಅ', 'आ' -> 'ಆ',  'इ' -> 'ಇ', 'ई' -> 'ಈ',
  'उ' -> 'ಉ', 'ऊ' -> 'ಊ',
  'ऋ' -> 'ಋ', 'ॠ' -> 'ೠ', 'ऌ' -> 'ಌ', 'ॡ' -> 'ೡ', 
  'ऎ' -> 'ಎ',
  'ए' -> 'ಏ',
  'ऐ' -> 'ಐ',
  'ऒ' -> 'ಒ',
  'ओ' -> 'ಓ', 'औ' -> 'ಔ',
  'ा' -> 'ಾ',
  'ि' -> 'ಿ', 
  'ी' -> 'ೀ',
  'ु' -> 'ು', 'ू' -> 'ೂ',
  'ृ' -> 'ೃ', 'ॄ' -> 'ೄ',
  'ॢ' -> 'ೢ', 'ॣ' -> 'ೣ',
  'ॆ' -> 'ೆ',
  'े' -> 'ೇ',
  'ै' ->  'ೈ',
  'ॊ' -> 'ೊ',
  'ो' -> 'ೋ',  'ौ' -> 'ೌ',
  'ह' -> 'ಹ', 'य' -> 'ಯ', 'व' -> 'ವ', 'र' -> 'ರ', 'ल' -> 'ಲ',
  'ञ' -> 'ಞ',
  'ङ' -> 'ಙ',
  'म' -> 'ಮ', 
  'ण' -> 'ಣ',
  'न' -> 'ನ',
  'झ' -> 'ಝ', 'भ' -> 'ಭ',
  'घ' -> 'ಘ', 'ढ' -> 'ಢ', 'ध' -> 'ಧ',
  'ज' -> 'ಜ', 'ब' -> 'ಬ', 'ग' -> 'ಗ',
  'ड' -> 'ಡ', 'द' -> 'ದ',
  'ख' -> 'ಖ',
  'फ' -> 'ಫ', 'छ' -> 'ಛ', 'ठ' -> 'ಠ',
  'थ' -> 'ಥ', 'च' -> 'ಚ', 'ट' -> 'ಟ', 'त' -> 'ತ', 
  'क' -> 'ಕ', 'प' -> 'ಪ',
  'श' -> 'ಶ', 'ष' -> 'ಷ', 'स' -> 'ಸ',
  'ळ' -> 'ಳ', '्' -> '್', 'ं' -> 'ಂ',  'ः' -> 'ಃ',
  'ऽ' -> 'ಽ', '़' -> '಼',
  '०' -> '೦', '१'-> '೧', '२'-> '೨',
  '३'-> '೩', '४'-> '೪', '५'-> '೫',
  '६'-> '೬', '७'-> '೭', '८'-> '೮', '९'-> '೯'
  )

  override val mapToDevanagari = mapFromDevanagari.map(_.swap)

}


// ACKNOWLEDGEMENT:
// The kannaDa table was originally created.
// Produced using shrI vinod rAjan's
// akSharamukha service ( http://www.virtualvinodh.com/aksaramukha ).

object telugu extends IndicScript{
// Produced using shrI vinod rAjan's
// akSharamukha service ( http://www.virtualvinodh.com/aksaramukha ).
  override val mapFromDevanagari = Map(
  'अ' -> 'అ', 'आ' -> 'ఆ',  'इ' -> 'ఇ', 'ई' -> 'ఈ',
  'उ' -> 'ఉ', 'ऊ' -> 'ఊ',
  'ऋ' -> 'ఋ', 'ॠ' -> 'ౠ', 'ऌ' -> 'ఌ', 'ॡ' -> 'ఌ', 
  'ऎ' -> 'ఎ',
  'ए' -> 'ఏ',
  'ऐ' -> 'ఐ',
  'ऒ' -> 'ఒ',
  'ओ' -> 'ఓ', 'औ' -> 'ఔ',
  'ा' -> 'ಾ',
  'ि' -> 'ಿ', 
  'ी' -> 'ೀ',
  'ु' -> 'ು', 'ू' -> 'ೂ',
  'ृ' -> 'ೃ', 'ॄ' -> 'ೄ', 'ॣ' -> 'ೄ',
  'ॢ' -> 'ೄ', 'ॆ' -> 'ೆ',
  'े' -> 'ೇ',
  'ै' ->  'ೈ',
  'ॊ' -> 'ೊ',
  'ो' -> 'ೋ',  'ौ' -> 'ೌ',
  'ह' -> 'హ', 'य' -> 'య', 'व' -> 'వ', 'र' -> 'ర', 'ल' -> 'ల',
  'ञ' -> 'ఞ',
  'ङ' -> 'ఙ',
  'म' -> 'మ', 
  'ण' -> 'ణ',
  'न' -> 'న',
  'झ' -> 'ఝ', 'भ' -> 'భ',
  'घ' -> 'ఘ', 'ढ' -> 'ఢ', 'ध' -> 'ధ',
  'ज' -> 'జ', 'ब' -> 'బ', 'ग' -> 'గ',
  'ड' -> 'డ', 'द' -> 'ద',
  'ख' -> 'ఖ',
  'फ' -> 'ఫ', 'छ' -> 'ఛ', 'ठ' -> 'ఠ',
  'थ' -> 'థ', 'च' -> 'చ', 'ट' -> 'ట', 'त' -> 'త', 
  'क' -> 'క', 'प' -> 'ప',
  'श' -> 'శ', 'ष' -> 'ష', 'स' -> 'స',
  'ळ' -> 'ళ', '्' -> '್', 'ं' -> 'ం',  'ः' -> 'ః',
  'ऽ' -> 'ఽ', '़' -> '़', 'ँ' -> 'ఁ',
  '०' -> '౦', '१'-> '౧', '२'-> '౨',
  '३'-> '౩', '४'-> '౪', '५'-> '౫',
  '६'-> '౬', '७'-> '౭', '८'-> '౮', '९'-> '౯'
  )

override val mapToDevanagari = mapFromDevanagari.map(_.swap)

}


