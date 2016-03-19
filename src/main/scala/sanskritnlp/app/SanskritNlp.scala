package sanskritnlp.app

import sanskritnlp.vyAkaraNa._
import sanskritnlp.transliteration._
import org.slf4j.LoggerFactory

object sanskritNlp {
  val log = LoggerFactory.getLogger(this.getClass)
  
//  The file whence parameters such as laungage, corpus, taggerType are read.
  var RUNTIME_SETTINGS_FILE = getClass.getResource("/default/runtimeSettings.properties").getPath
  val props = new java.util.Properties

    val file = new java.io.FileInputStream(RUNTIME_SETTINGS_FILE)
    props.load(file)
    file.close
  


  /**
   * @param args the command line arguments:
   *  args(1), if it exists, is assumed to be RUNTIME_SETTINGS_FILE
   *
   */
  def main(args: Array[String]): Unit = {
    if(args.length < 0) log error "Need an argument."
    args(0) match {
      case "bot" => {
        //new Wiktionary().test
      }
      case _ => println("No program yet.")
    }
  }
}

object sanskritNlpTest {
  def main(args: Array[String]): Unit = {
    shivasUtra.test
    vowels.test
    kannaDa.test
  }
}
