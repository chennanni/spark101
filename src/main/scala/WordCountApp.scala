import org.apache.spark.sql.SparkSession

/**
 * count a, b occurrences in one file
 */
object WordCountApp {

  def main(args: Array[String]) {
    val path = System.getProperty("user.dir")
    val logFile = path + "/README.md"
    println("reading file at:" + logFile)

    val spark = SparkSession
      .builder
      .appName("Simple Application")
      .master("local")
      .getOrCreate()

    val logData = spark.read.textFile(logFile).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()

    println(s"Lines with a: $numAs, Lines with b: $numBs")
    spark.stop()
  }

}