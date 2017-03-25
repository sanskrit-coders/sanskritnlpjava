# Users
## Library users
* Maven repository being built..

### Built output
* Final jar files
  * out/*.jar
  * target/*.jar
* War files
  * target/
* Classes
  * out/production/*/
  * target/.../classes

### Some known users
* [stardict-sanskrit]() and sister stardict-.* projects.


## Web-UI users
* The corresponding webserver hosts pages such as: <http://sanskritnlp.appspot.com/forms/Chandas-de.htm> etc..
    * (These are listed from <https://sites.google.com/site/sanskritcode/projects>.)

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
* Regarding **maven targets**: You can set up a maven goal in intellij as well.

### Deploying to google cloud.
* Note that we're using appengine-maven-plugin in <pom.xml>.
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
* [Under construction.]

### Building a jar.
* Simplest way is to set up a build artifact in intellij IDea.
* There is also a maven target in pom.xml which I've used occasionally.