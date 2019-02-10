package utils

import org.apache.accumulo.core.client.BatchWriter
import org.apache.accumulo.core.data.{Mutation, Value}

import scala.io.Source

object Utils {

  private val sep: Char = ','

  def fillAccumuloTable(batchWriter : BatchWriter, data: String): Unit ={
    val source: Iterator[String] = Source.fromResource(data).getLines
    val header: List[String] = source.take(1).toString()
      .split(sep)
      .drop(1)
        .toList

    source.drop(1)

    source.foreach(x => {
      batchWriter.addMutation(getMutation(x, sep, header))
    })

    batchWriter.close()
    batchWriter.flush()
  }

  private def getMutation(line : String, separator: Char, header: List[String]) : Mutation ={
    val data = line.split(separator).toList
    val mutation = new Mutation(data.head)
    val dataToWrite: List[(String, String)] = header.zip(data.tail)

    dataToWrite.foreach{case(header, value) => {
      mutation.put(header, value, new Value)
    }}

    mutation
  }
}
