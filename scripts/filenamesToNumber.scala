import sys.process._
val dir = "/home/vvasuki/Dropbox/podcasts/samskrtam/सुभाषितानि/नीतिशतकम्/"
val result_str = s"ls $dir".!!
val files = result_str.trim.split('\n').sorted
println(files.length)
files.zip(0 to files.length - 1).foreach(x => {
  val oldFileName = x._1
  val seqNum = x._2
  // val Array(basename, extension) = oldFileName.split('.')
  val extension = "mp3"
  val newFileName = f"$seqNum%03d.$extension%s"
  s"cp $dir$oldFileName $dir$newFileName".!
})