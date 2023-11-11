# spark 101

This is a tutorial project for spark.

Topic 1

- HelloWorld
- GetProjectPath

Topic 2

- WordCountApp
  - Count a, b occurrences in one file
- MnmCountApp
    - Analyze the MnM color of each state given input file

## Env Setup

- install JDK
- install Spark
  - http://spark.apache.org/downloads.html 
- install sbt

## Build

`sbt package`

## Run

~~~
./bin/spark-submit \
--class <main-class>
--master <master-url> \
--deploy-mode <deploy-mode> \
--conf <key>=<value> \
<application-jar> \
[application-arguments]
~~~
