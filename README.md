# Introduction
A collection of scala and java classes for some basic natural language processing (NLP) for the Sanskrit language, contributed by the open source SanskritNLP project and friends.
Some notable facilities:
* Transliterate text from one script or encoding scheme to another.
* Deal with babylon dictionaries.
* Use bots to write to wiki projects (wiktionary, wikisource etc..).
* Basic metre identification.
* Some grammar simulation.

Contributions and suggestions are invited at https://github.com/sanskrit-coders/sanskritnlpjava . (Sister projects there may also be of interest.)

# Users
## Library users
* Maven repository [here](http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22sanskritnlp%22) .
* Last update : 2017-03-23

### Built output
* Final jar files
  * out/*.jar [all modules in intellij project]
  * target/*.jar [includes sources and javadocs in separate jars. sanskritnlp module only]
* War files
  * target/ [sanskritnlp module only]
* Classes
  * out/production/*/ [Modules other than sanskritnlp.]
  * target/sanskritnlp-1.0-SNAPSHOT/WEB-INF/classes [sanskritnlp module output.]

### Some known users
* [stardict-sanskrit]() and sister stardict-.* projects.


## Web-UI users
* The corresponding webserver hosts pages such as: <http://sanskritnlp.appspot.com/forms/Chandas-de.htm> etc..
    * (These are listed from <https://sites.google.com/site/sanskritcode/projects>.)
* Last update : 2017-03-23

# Contributors
## Setup
* Strongly recomment Intellij Idea IDE.
  * Just point it to the IML file and .idea/* files.
* There are also eclipse files which haven't been used in a long time.

## Where is the code?
* src/main/ has subfolders called java, scala. They respectively contain code written in those languages.
* Javascript code is in war/js.
* HTML forms are found in war/forms.

## Adding libraries
* Using IntelliJ Idea IDE : Don't muck around with pom.xml. Use project or module settings.

## Scala programming tips:
* Using IntelliJ Idea IDE :
  * use provided project file. Install the scala plugin. Set up the source folders in the module.
  * Open any valid singleton with a main function. Run it using Ctrl Shift F10.
* Building with maven:
  * mvn3 clean install
* Using Eclipse: Just use eclipse with the scalaIDE plugin. Set output folder WEB-INF/classes.

## Deployment
* Regarding **maven targets**:
  * You can set up a maven goal in intellij as well.
  * In intellij: Don't be fooled by weird messages in the Run widget - look at the messages widget.

### Deploying to google cloud.
* Note that we're using appengine-maven-plugin in <pom-war.xml>. In intellij pass this file explicitly with -f pom-war.xml.
  * It's use is described [here](https://cloud.google.com/appengine/docs/standard/java/tools/maven).
* `mvn appengine:update` should do the trick - if you have right maven version.
  * If you are doing this from intellij:
    * a browser window will pop up, get SSO authentication done,
    * give you a code to paste in the maven widget.
* Local build: `mvn clean install`
  * Files are output in target/ .
* Local dev server run: `mvn appengine:devserver`
* Manage online at <https://console.cloud.google.com> . Navigate around a bit.

### Releasing to maven.
* Note that we're using appengine-maven-plugin in <pom.xml>, and credentials stored in settings.xml (<-- not to be checked in) .
* Deploy snapshot artifacts into repository <https://oss.sonatype.org/content/repositories/snapshots/com/github/sanskrit-coders/sanskritnlp>.
  * Version number ends with -SNAPSHOT. Eg. 1.0-SNAPSHOT
  * Build target: clean deploy.
  * intellij target name: "mvn deploy".
* Deploy release artifacts into the [staging repository](https://oss.sonatype.org/content/repositories/releases/com/github/sanskrit-coders/sanskritnlp/) and [here](http://repo1.maven.org/maven2/com/github/sanskrit-coders/sanskritnlp/) :
  * Repeat the same with a non snapshot version number.
* Releasing to central (if it does not automatically happen):
  * Notes: <http://central.sonatype.org/pages/releasing-the-deployment.html>
  * Artifacts can be examined on Sonatype [here](https://oss.sonatype.org/#nexus-search;quick~sanskrit) and released - if the staging repository is visible there. Otherwise, it may already be deployed in central!
  * Maven target can be used: nexus-staging:release . There is an intellij target of the same name.
  * "After you successfully release, your component will be published to Central, typically within 10 minutes, though updates to search.maven.org can take up to two hours."
* Project was created under Sonatype:  [here](https://issues.sonatype.org/browse/OSSRH-29183) .

### Building a jar.
* Simplest way is to set up a build artifact in intellij IDea.
* There was also a maven target in pom.xml which I've used occasionally.

## Technical choices
### Scala
* One can write much more concise code (1/4th to 1/3rd relative to Java and 3/4ths to 5/6ths relative to Python, according to [this](http://bcomposes.com/2012/03/01/student-questions-about-scala-part-2/) )
  * For example, the ease with which one can iterate in scala using higher order functions (the maps, filters and zips above) available with scala's excellent collections library.
* while not sacrificing the ability to use java libraries, and readability/ speed of java.
* It is increasing in popularity relative to competitors : scala vs clojure ( [Google trends](https://trends.google.com/trends/explore?date=all&q=Scala%20tutorial,Clojure%20tutorial) ), scala vs julia ( [Google trends](https://trends.google.com/trends/explore?date=all&q=Scala%20tutorial,Julia%20tutorial) ).
* [Here](http://bcomposes.com/2011/08/22/first-steps-in-scala-for-first-time-programmers-part-1/) is a good series of blog posts which provide an introduction to Scala.
