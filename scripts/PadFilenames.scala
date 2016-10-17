import java.io.File
import sys.process._

def recursiveListFiles(f: File): Array[File] = {
  val these = f.listFiles
  these ++ these.filter(_.isDirectory).flatMap(recursiveListFiles)
}
def rAmAyaNaFileRename() = {
  val dir = "/home/vvasuki/Dropbox/podcasts/samskrtam/rAmAyaNam/"
  val result_str = s"ls $dir".!!
  val files = result_str.trim.split('\n')
  println(files.length)
  files.map((file: String) => {
    val Array(basename, extension) = file.split('.')
    val Array(kANDa, sarga, shloka) = basename.split('-').map(_.toInt)
    val newFileName = f"$kANDa%d-$sarga%02d-$shloka%02d.mp3"
    (file, newFileName)
  }).filter(tuple => tuple._1 != tuple._2).foreach(tuple => {
    val oldFileName = tuple._1
    val newFileName = tuple._2
    s"mv $dir/$oldFileName $dir$newFileName".!
  })
}

def vedaFilesRename() = {
  val dir = "/home/vvasuki/Videos/ihg"
  val files = recursiveListFiles(new File(dir))
  println(files.length)
  val regex_part_file = """00-Part(\d+)(.+)""".r
  val regex_topic_file = """00-Topic(\d+)(.+)""".r
  val regex_overview_file = """00([^\d]+)Overview(.+)""".r
  files.filter(_.getName contains "mp3").foreach(file => {
    var newFileName = "UNKNOWN " + file.getName
    file.getName match {
      case regex_part_file(num, title) => {
        newFileName = f"Part-${num.toInt}%02d-$title"
      }
      case regex_topic_file(num, title) => {
        newFileName = f"Part-${num.toInt}%02d-$title"
      }
      case regex_overview_file(prefix, title) => {
        newFileName = f"Part-00-Overview-$title"
      }
      case _ => { newFileName = file.getName.replace("00-", "")}
    }
    file.renameTo(new File(s"${file.getParent}/$newFileName"))
  })
}

vedaFilesRename()