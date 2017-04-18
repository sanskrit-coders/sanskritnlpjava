package sanskritnlp.utils


object collectionUtils {

  def toJava(x: Any): Any = {
    import scala.collection.JavaConverters._
    x match {
      case y: scala.collection.MapLike[_, _, _] =>
        y.map { case (d, v) => toJava(d) -> toJava(v) } asJava
      case y: scala.collection.SetLike[_,_] =>
        y map { item: Any => toJava(item) } asJava
      case y: Iterable[_] =>
        y.map { item: Any => toJava(item) } asJava
      case y: Iterator[_] =>
        toJava(y.toIterable)
      case _ =>
        x
    }
  }

  def toScala(x: Any): Any = {
    import collection.JavaConversions._
    x match {
      case y: java.util.Map[_, _] =>
        mapAsScalaMap(y).map{ case (d, v) => toScala(d) -> toScala(v) }
      case y: java.lang.Iterable[_] =>
        iterableAsScalaIterable(y).toList.map { item: Any => toScala(item) }
      case y: java.util.Iterator[_] =>
        toScala(y)
      case _ =>
        x
    }
  }
}
