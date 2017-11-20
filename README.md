# Introduction
A collection of java classes for some basic natural language processing (NLP) for the Sanskrit language, contributed by the open source SanskritNLP project and friends.
Some notable facilities:
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
* Classes
  * out/production/*/ [Modules other than sanskritnlp.]
  * target/sanskritnlp-1.0-SNAPSHOT/WEB-INF/classes [sanskritnlp module output.]

### Some known users
* [stardict-sanskrit]() and sister stardict-.* projects.



# Contributors
## Setup
* Strongly recomment Intellij Idea IDE.
  * Just point it to the IML file and .idea/* files.
* There are also eclipse files which haven't been used in a long time.

## Deployment
### Regarding **maven targets** in intellij [Deprecated]
  * Easiest: Use the View -> Tool Windows -> "Maven Projects" window.  
  * You can set up a maven goal as a run configuration.
  * In intellij: Don't be fooled by weird messages in the Run widget - look at the messages widget.

### Releasing to maven.
* Note that we're using appengine-maven-plugin in <pom.xml>, and credentials stored in settings.xml (<-- not to be checked in) .
* Deploy snapshot artifacts into repository <https://oss.sonatype.org/content/repositories/snapshots/com/github/sanskrit-coders/sanskritnlp>.
  * Version number ends with -SNAPSHOT. Eg. 1.0-SNAPSHOT
  * Build and release target: clean deploy.
  * intellij target name: "mvn deploy".
* Deploy release artifacts into the [staging repository](https://oss.sonatype.org/content/repositories/releases/com/github/sanskrit-coders/sanskritnlp/) and [here](http://repo1.maven.org/maven2/com/github/sanskrit-coders/) :
  * Repeat the same with a non snapshot version number.
* Releasing to central (if it does not automatically happen):
  * Notes: <http://central.sonatype.org/pages/releasing-the-deployment.html>
  * Artifacts can be examined on Sonatype [here](https://oss.sonatype.org/#nexus-search;quick~sanskrit) and released - if the staging repository is visible there. Otherwise, it may already be deployed in central!
  * Maven target can be used: nexus-staging:release . There is an intellij target of the same name.
  * "After you successfully release, your component will be published to Central, typically within 10 minutes, though updates to search.maven.org can take up to two hours."
* Group id was created under Sonatype:  [here](https://issues.sonatype.org/browse/OSSRH-29183) , and we received exemption from the javadoc requirement alter ([link](https://issues.sonatype.org/browse/MVNCENTRAL-2253?filter=-2)).


## Links to general comments
See [indic-transliteration/README](https://github.com/sanskrit-coders/indic-transliteration/blob/master/README.md) for the following info:

  - [Setup](https://github.com/sanskrit-coders/indic-transliteration/blob/master/README.md#setup)
  - [Deployment](https://github.com/sanskrit-coders/indic-transliteration/blob/master/README.md#deployment)
    - [Regarding **maven targets** in intellij](https://github.com/sanskrit-coders/indic-transliteration/blob/master/README.md#regarding-**maven-targets**-in-intellij)
    - [Releasing to maven.](https://github.com/sanskrit-coders/indic-transliteration/blob/master/README.md#releasing-to-maven.)
    - [Building a jar.](https://github.com/sanskrit-coders/indic-transliteration/blob/master/README.md#building-a-jar.)
  - [Technical choices](https://github.com/sanskrit-coders/indic-transliteration/blob/master/README.md#technical-choices)
    - [Scala](https://github.com/sanskrit-coders/indic-transliteration/blob/master/README.md#scala)
    