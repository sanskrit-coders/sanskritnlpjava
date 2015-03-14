The corresponding webserver hosts pages such as: http://sanskritnlp.appspot.com/forms/Chandas-de.htm etc.. (These are listed from [https://sites.google.com/site/sanskritcode/projects])

Where is the code?
src/main/ has subfolders called java, scala. They respectively contain code written in those languages.

Javascript code is in war/js.

HTML forms are found in war/forms.

Scala programming tips:
* Building with maven:
  * mvn3 clean install
* Using Eclipse: Just use eclipse with the scalaIDE plugin. Set output folder WEB-INF/classes.
* Using IntelliJ Idea IDE : 
  * use provided project file. Install the scala plugin. Set up the source folders in the module.
  * Open any valid singleton with a main function. Run it using Ctrl Shift F10.

