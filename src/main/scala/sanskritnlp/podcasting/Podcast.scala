package sanskritnlp.podcasting

import scala.xml.Node
import scala.xml.Elem

/**
  * Created by vvasuki on 10/16/16.
  * Format example: http://www.podcast411.com/howto_1.html
  */
class Podcast(val title: String, val description: String, val link: String = "http://www.podcast411.com", val webmaster: String, val audioFiles: List[String]) {
  def add(n:Node,c:Node):Node = n match { case e:Elem => e.copy(child=e.child++c) }

  def getFeed(): Node = {
    var feed =
      <rss xmlns:itunes="http://www.itunes.com/dtds/podcast-1.0.dtd" version="2.0">
        <channel>

          <title>{title}</title>
          <description>{description}</description>
          <link>{link}</link>
          <language>en-us</language>
          <copyright>None</copyright>
          <lastBuildDate>Sat, 25 Mar 2006 11:30:00 -0500</lastBuildDate>
          <pubDate>Sat, 25 Mar 2006 11:30:00 -0500</pubDate>
          <docs>http://blogs.law.harvard.edu/tech/rss</docs>
          <webMaster>{webmaster}</webMaster>
          <itunes:author>{webmaster}</itunes:author>
          <itunes:subtitle>{description}</itunes:subtitle>
          <itunes:summary>{description}</itunes:summary>

          <itunes:owner>
            <itunes:name>{webmaster}</itunes:name>
            <itunes:email>{webmaster}</itunes:email>
          </itunes:owner>

          <itunes:explicit>No</itunes:explicit>

          <itunes:image href="http://www.podcast411.com/img/411_itunes.jpg"/>

          <itunes:category text="Technology">
            <itunes:category text="Podcasting"/>
          </itunes:category>
        </channel>

      </rss>
    return feed
  }
}

object shAstraPodcastMaker {
  def main(args: Array[String]): Unit = {
    val podcast = new Podcast(title="shastras in sanskrit संस्कृतशास्त्राणि", description = "", webmaster = "", audioFiles = List("a.mp3"))
    print(podcast.getFeed())
  }
}