package sanskritnlp.subhAShita

import net.liftweb.json._
import net.liftweb.json.Serialization
import org.slf4j.LoggerFactory
import sanskritnlp.transliteration.transliterator

case class ScriptRendering(text: String, scheme: String = transliterator.scriptUnknown,
                           var startLetter: String = "") {
  startLetter = text.toList.head.toString
}
case class Source(name: ScriptRendering, authors: List[ScriptRendering])
case class Rating(rating: Int)

abstract class Annotation(val key: String, val source: Source)
case class Language(code: String)
case class Topic(scriptRendering: ScriptRendering, language: Language = Language("UNK"))
case class TopicAnnotation(override val key: String, override val source: Source, topic: Topic) extends Annotation(key, source)
case class MemorableBitsAnnotation(override val key: String, override val source: Source, memorableBits: List[ScriptRendering]) extends Annotation(key, source)
case class RatingAnnotation(override val key: String, override val source: Source, overall: Rating) extends Annotation(key, source)
case class DescriptionAnnotation(override val key: String, override val source: Source, translation: ScriptRendering, language: Language = Language("UNK")) extends Annotation(key, source)
case class OriginAnnotation(override val key: String, override val source: Source, origin: Source) extends Annotation(key, source)

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

// We won't store this document in the database - we will use related documents strategy. See https://developer.couchbase.com/documentation/mobile/1.4/guides/couchbase-lite/native-api/data-modeling/index.html .
// We just keep this since it's convenient for passing stuff around.
case class QuoteInfo(quoteText: QuoteText,
                     originAnnotations: List[OriginAnnotation] = List(),
                     topicAnnotations: List[TopicAnnotation] = List(),
                     ratingAnnotations: List[RatingAnnotation] = List(),
                     descriptionAnnotations: List[DescriptionAnnotation] = List()
                    )

object subhAShitaTest {
  val log = LoggerFactory.getLogger(getClass.getName)
  def quoteTest: Unit = {
    val quoteText = QuoteText(
        scriptRenderings = List(
          ScriptRendering(text = "दण्डः शास्ति प्रजाः सर्वाः दण्ड एवाभिरक्षति। दण्डः सुप्तेषु जागर्ति दण्डं धर्मं विदुर्बुधाः।। \tदण्डः\t",
            scheme = transliterator.scriptDevanAgarI)),
        language = Language("sa"))
    // implicit val formats = Serialization.formats(NoTypeHints)
    implicit val formats = Serialization.formats(ShortTypeHints(
      List(
        classOf[QuoteText],
        classOf[OriginAnnotation],
        classOf[DescriptionAnnotation],
        classOf[TopicAnnotation],
        classOf[RatingAnnotation]
      )))
    log info Serialization.writePretty(quoteText)

    val origin = OriginAnnotation(key = quoteText.key, source =
      Source(ScriptRendering(text = "विश्वाससङ्ग्रहः", scheme = transliterator.scriptDevanAgarI),
        authors = List(ScriptRendering(text = "विश्वासः", scheme = transliterator.scriptDevanAgarI))),
      origin = Source(ScriptRendering(text = "kashcit", scheme = "en"),
        authors = List(ScriptRendering(text = "kvacit", scheme = transliterator.scriptDevanAgarI))))
    log info Serialization.writePretty(origin)
  }

  def main(args: Array[String]): Unit = {
    // SanskritSubhashitaTest
    quoteTest
  }
}
