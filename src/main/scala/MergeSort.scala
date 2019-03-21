import java.io.{BufferedWriter, File, FileWriter}

import scala.io.Source

object MergeSort {
  def main(args: Array[String]): Unit = {

    val path = "/Users/mnetreba/Downloads/TextSort/src/text"
    val file = Source.fromFile(path)

    val groups = getChunks(file, 2000)

    for (line <- groups)
      writeToFile(mergeSort(line.toList))
  }


  def getChunks(file: Source, size: Int) ={
    file.getLines().map(_.toString).grouped(size)
  }

  def mergeSort(xs: List[String]): List[String] = {
    val n = xs.length / 2
    if (n == 0) xs
    else {
      def merge(xs: List[String], ys: List[String]): List[String] =
        (xs, ys) match {
          case(Nil, ys) => ys
          case(xs, Nil) => xs
          case(x :: xs1, y :: ys1) =>
            if (x < y) x::merge(xs1, ys)
            else y :: merge(xs, ys1)
        }
      val (left, right) = xs splitAt(n)
      merge(mergeSort(left), mergeSort(right))
    }
  }


  def writeToFile(chunk: Seq[String]): Unit = {
    val path = "/Users/mnetreba/Downloads/TextSort/src/new_text"
    val file = new File(path)
    val bw = new BufferedWriter(new FileWriter(file, true))
    for (line <- chunk)
      bw.write(line)
    bw.close()
  }
}
