// scalastyle:off println

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

/**
 *
 * Count the MnM color of each state
 *
 *  Usage: MnmCountApp <mnm_file_dataset>
 *  Example: spark-submit --class main.scala.MnmCountApp jars/main-scala-chapter2_2.12-1.0.jar data/mnm_dataset.csv
 *
 */
object MnmCountApp {
  def main(args: Array[String]) {
    val spark = SparkSession
      .builder
      .appName("MnMCount")
      .master("local")
      .getOrCreate()

    // get the M&M data set file name
    var mnmFile = ""
    if (args.length < 1) {
      mnmFile = System.getProperty("user.dir") + "/src/main/scala" + "/data/mnm_dataset.csv"
      print("read file from default location: " + mnmFile)
    } else {
      mnmFile = args(0)
      print("read file from input location: " + mnmFile)
    }

    // read the file into a Spark DataFrame
    val mnmDF = spark.read.format("csv")
      .option("header", "true")
      .option("inferSchema", "true")
      .load(mnmFile)
    // display DataFrame
    println("show a snapshot of input data")
    mnmDF.show(5, false)

    // TASK 1: aggregate count of all colors and groupBy state and color, orderBy descending order
    val countMnMDF = mnmDF.select("State", "Color", "Count")
      .groupBy("State", "Color")
      .sum("Count")
      .orderBy(desc("sum(Count)"))

    // show all the resulting aggregation for all the dates and colors
    println("show all the resulting aggregation for all the dates and colors")
    countMnMDF.show(60)
    println(s"Total Rows = ${countMnMDF.count()}")

    // TASK 2: find the aggregate count for California by filtering
    val caCountMnNDF = mnmDF.select("*")
      .where(col("State") === "CA")
      .groupBy("State", "Color")
      .sum("Count")
      .orderBy(desc("sum(Count)"))

    // show the resulting aggregation for California
    println("show the resulting aggregation for California")
    caCountMnNDF.show(10)
    println("end")
  }
}
// scalastyle:on println
