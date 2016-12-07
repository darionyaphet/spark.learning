package org.darion.yaphet.spark.scala.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by darion.johannes.yaphet on 16/3/29.
  */
object LocalExample {
  def main(args: Array[String]) {
    println("Locality Example is Running")
    val conf = new SparkConf().setMaster("spark://darionjohannesyaphetdeMacBook-Pro.local:7077").setAppName("StreamingExample")
    val context = new StreamingContext(conf, Seconds(3))

    val lines = context.socketTextStream("127.0.0.1", 9999)
    val words = lines.flatMap(_.split(" "))
    val wordCounts = words.map(x => (x, 1)).reduceByKey(_ + _)
    wordCounts.print()

    context.start()
    context.awaitTermination()
  }
}
