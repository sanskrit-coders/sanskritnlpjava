# Introduction
A collection of scala and java classes for some basic natural language processing (NLP) for the Sanskrit language, contributed by the open source SanskritNLP project and friends.
Some notable facilities:
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

## Links to general comments
See [indic-transliteration/README](https://github.com/sanskrit-coders/indic-transliteration/blob/master/README.md) for the following info:

  - [Setup](https://github.com/sanskrit-coders/indic-transliteration/blob/master/README.md#setup)
  - [Deployment](https://github.com/sanskrit-coders/indic-transliteration/blob/master/README.md#deployment)
    - [Regarding **maven targets** in intellij](https://github.com/sanskrit-coders/indic-transliteration/blob/master/README.md#regarding-**maven-targets**-in-intellij)
    - [Releasing to maven.](https://github.com/sanskrit-coders/indic-transliteration/blob/master/README.md#releasing-to-maven.)
    - [Building a jar.](https://github.com/sanskrit-coders/indic-transliteration/blob/master/README.md#building-a-jar.)
  - [Technical choices](https://github.com/sanskrit-coders/indic-transliteration/blob/master/README.md#technical-choices)
    - [Scala](https://github.com/sanskrit-coders/indic-transliteration/blob/master/README.md#scala)
    