# Users
## Library users
* Maven repository being built..

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
* Note that we're using appengine-maven-plugin in <pom.xml>.
* Snapshots are deployed to < https://oss.sonatype.org/content/repositories/snapshots/com/github/sanskrit-coders/sanskritnlp>.
  * Version number ends with -SNAPSHOT. Eg. 1.0-SNAPSHOT
  * Build target: clean deploy.
* Release: Repeat the same with a non snapshot version number.
* Releasing to central:
  * Notes: <http://central.sonatype.org/pages/releasing-the-deployment.html> TODO.
  * Artifacts can be examined on Sonatype [here](https://oss.sonatype.org/#nexus-search;quick~sanskrit) .
* Project was created under Sonatype:  [here](https://issues.sonatype.org/browse/OSSRH-29183) .

### Building a jar.
* Simplest way is to set up a build artifact in intellij IDea.
* There was also a maven target in pom.xml which I've used occasionally.