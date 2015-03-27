import sys.process._
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