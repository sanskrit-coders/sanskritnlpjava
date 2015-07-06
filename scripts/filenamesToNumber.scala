import sys.process._
val dir = "/home/vvasuki/Dropbox/podcasts/samskrtam/raghuvaMshaH/sarga1/"
val result_str = s"ls $dir".!!
val files = result_str.trim.split('\n').sorted
println(files.length)
files.zip(1 to files.length).foreach(x => {
  val oldFileName = x._1
  val seqNum = x._2
  // val Array(basename, extension) = oldFileName.split('.')
  val extension = "mp3"
  val newFileName = f"$seqNum%03d.$extension%s"
  s"cp $dir/$oldFileName $dir/$newFileName".!
})