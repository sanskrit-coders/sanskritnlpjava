package sanskritnlp.subhAShita

import net.liftweb.json._
import net.liftweb.json.Serialization
import org.slf4j.LoggerFactory
import sanskritnlp.transliteration.transliterator

case class Source(name: String, authors: List[String])
case class Rating(rating: Int)

abstract class Annotation(val source: Source)
case class ScriptRendering(text: String, scheme: String = transliterator.scriptUnknown,
                           var startLetter: String = "") {
  startLetter = text.toList.head.toString
}
case class Language(code: String)
case class Topic(scriptRendering: ScriptRendering, language: Language = Language("UNK"))
case class TopicAnnotation(override val source: Source, topics: List[Topic]) extends Annotation(source)
case class MemorableBitsAnnotation(override val source: Source, memorableBits: List[ScriptRendering]) extends Annotation(source)
case class RatingAnnotation(override val source: Source, overall: Rating) extends Annotation(source)
case class DescriptionAnnotation(override val source: Source, description: String) extends Annotation(source)
case class OriginAnnotation(override val source: Source, origin: Source) extends Annotation(source)

case class QuoteText(scriptRenderings: List[ScriptRendering],
                     var key: String = "",
                     language: Language = Language("UNK"),
                     metre: Option[String] = None) {
  language match {
    case Language("sa") => {
      val text = scriptRenderings.filter(_.scheme == transliterator.scriptDevanAgarI).head.text
      key =
      transliterator.transliterate(
        text.replaceAll("\\P{IsDevanagari}", "").replaceAll("[।॥०-९]+", "").replaceAll("\\s", ""), "dev", "optitrans")
        .replaceAll("[MNn]", "m")
    }
    case Language("en") => {
      key = scriptRenderings.filter(_.scheme == "en").head.text.replaceAll("\\s", "")
    }
  }
}

case class QuoteInfo(quoteText: QuoteText,
                     originAnnotations: List[OriginAnnotation] = List(),
                     topicAnnotations: List[TopicAnnotation] = List(),
                     ratingAnnotations: List[RatingAnnotation] = List(),
                     descriptionAnnotations: List[DescriptionAnnotation] = List()
                    )

object subhAShitaTest {
  val log = LoggerFactory.getLogger(getClass.getName)
  def quoteTest: Unit = {
    val quoteInfo = QuoteInfo(
      quoteText = QuoteText(
        scriptRenderings = List(
          ScriptRendering(text = "दण्डः शास्ति प्रजाः सर्वाः दण्ड एवाभिरक्षति। दण्डः सुप्तेषु जागर्ति दण्डं धर्मं विदुर्बुधाः।। \tदण्डः\t",
            scheme = transliterator.scriptDevanAgarI)),
        language = Language("sa")),
      originAnnotations = List(
        OriginAnnotation(source = Source("विश्वाससङ्ग्रहः", List("विश्वासः")), origin = Source("क्वचित्", List("कश्चित्")))
      )
    )
    implicit val formats = Serialization.formats(NoTypeHints)
    log info Serialization.writePretty(quoteInfo)
  }

  def main(args: Array[String]): Unit = {
    // SanskritSubhashitaTest
    quoteTest
  }
}
