import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.catalyst.plans.physical.RangePartitioning

object Sorting {

  def main(args: Array[String]): Unit = {
    sort()
  }

  def sort(): Unit ={

    val spark = SparkSession
      .builder
      .master("local[2]")
      .appName("Sorting")
      .getOrCreate()

    import spark.implicits._

    val df = spark.read.csv("src/text").coalesce(10)

    df.sortWithinPartitions($"_c0").show()

  }

}