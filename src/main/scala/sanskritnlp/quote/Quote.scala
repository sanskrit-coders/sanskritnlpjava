package sanskritnlp.quote

import net.liftweb.json._
import net.liftweb.json.Serialization
import org.slf4j.LoggerFactory
import sanskritnlp.quote.subhAShitaTest.getClass
import sanskritnlp.transliteration.transliterator

case class Language(code: String, var canonicalScript: String = transliterator.scriptUnknown) {
  val log = LoggerFactory.getLogger(getClass.getName)
  code match {
    case "sa" => canonicalScript = transliterator.scriptDevanAgarI
    case "en" => canonicalScript = "en"
    case _ => canonicalScript = transliterator.scriptUnknown
  }

}
case class ScriptRendering(text: String, scheme: String = transliterator.scriptUnknown,
                           var startLetter: String = "") {
  startLetter = text.toList.head.toString
  val log = LoggerFactory.getLogger(getClass.getName)

  // A unique identifier for a text rendering.
  def getKey: String = {
    scheme match {
      case transliterator.scriptDevanAgarI => {
        return transliterator.transliterate(
          text.replaceAll("\\P{IsDevanagari}", "").replaceAll("[।॥०-९]+", "").replaceAll("\\s", ""), "dev", "optitrans")
          .replaceAll("[MNn]", "m")
      }
      case unknownScript => {
        log warn (s"got $unknownScript")
        return text.replaceAll("\\s", "")
      }
    }
  }
}

case class QuoteText(scriptRenderings: List[ScriptRendering],
                     var key: String = "",
                     language: Language = Language("UNK"),
                     metre: Option[String] = None) {
  val log = LoggerFactory.getLogger(getClass.getName)
  // Helps make primary keys (aka ids) for storing these case classes in databases.
  if (scriptRenderings.nonEmpty) {
    val canonicalRendering = scriptRenderings.filter(_.scheme == language.canonicalScript).headOption
    val rendering = canonicalRendering.getOrElse(scriptRenderings.head)
    key = rendering.getKey
  }
  def this(scriptRendering: ScriptRendering, language: Language) = this(scriptRendering::Nil, language=language)
}


case class Source(name: QuoteText, authors: List[QuoteText], var key: String = "") {
  key = s"${name.key}__${authors.sortBy(_.scriptRenderings.head.text).map(_.key).mkString("_")}"
}

case class Rating(rating: Int)

abstract class Annotation(val textKey: String, val source: Source, var key: String = "") {
  key = s"${textKey}__${source.key}"
}
case class Topic(scriptRendering: ScriptRendering, language: Language = Language("UNK"))
case class TopicAnnotation(override val textKey: String, override val source: Source, topic: Topic) extends Annotation(textKey, source)
case class MemorableBitsAnnotation(override val textKey: String, override val source: Source, memorableBits: List[ScriptRendering]) extends Annotation(textKey, source)
case class RatingAnnotation(override val textKey: String, override val source: Source, overall: Rating) extends Annotation(textKey, source)
case class DescriptionAnnotation(override val textKey: String, override val source: Source, translation: ScriptRendering, language: Language = Language("UNK")) extends Annotation(textKey, source)
case class OriginAnnotation(override val textKey: String, override val source: Source, origin: Source) extends Annotation(textKey, source)

// We won't store this document in the database - we will use related documents strategy. See https://developer.couchbase.com/documentation/mobile/1.4/guides/couchbase-lite/native-api/data-modeling/index.html .
// We just keep this since it's convenient for passing stuff around.
case class QuoteInfo(quoteText: QuoteText,
                     originAnnotations: List[OriginAnnotation] = List(),
                     topicAnnotations: List[TopicAnnotation] = List(),
                     ratingAnnotations: List[RatingAnnotation] = List(),
                     descriptionAnnotations: List[DescriptionAnnotation] = List()
                    )

object quoteTextHelper {
  def getSanskritDevangariiQuote(text: String): QuoteText =
    QuoteText(scriptRenderings=ScriptRendering(text = text, scheme = transliterator.scriptDevanAgarI)::Nil,
    language = Language("sa"))
}

object sourceHelper {
  def getSanskritDevanaagariiSource(title: String, authors: List[String]): Source = {
    return Source(quoteTextHelper.getSanskritDevangariiQuote(title),
      authors=authors.map(quoteTextHelper.getSanskritDevangariiQuote(_))
    )
  }
}

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

    val origin = OriginAnnotation(textKey = quoteText.key,
      source = sourceHelper.getSanskritDevanaagariiSource("विश्वाससङ्ग्रहः", List("विश्वासः")),
      origin = sourceHelper.getSanskritDevanaagariiSource("क्वचित्", List("कश्चित्"))
    )
    log info Serialization.writePretty(origin)
  }

  def main(args: Array[String]): Unit = {
    // SanskritSubhashitaTest
    quoteTest
  }
}
