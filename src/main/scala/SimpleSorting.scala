import java.io.{BufferedWriter, File, FileWriter}

import scala.io.Source

object SimpleSorting {

  def main(args: Array[String]): Unit = {

    val path = "/Users/mnetreba/Downloads/TextSort/src/text"
    val file = Source.fromFile(path)

    val groups = getChunks(file, 50000)

    for (line <- groups)
      sortNextChunk(line)

  }


  def getChunks(file: Source, size: Int) ={
    file.getLines().map(_.toString).grouped(size)
  }


  def sortNextChunk(chunk: Seq[String]): Unit = {
    writeToFile(chunk.sorted)
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