package org.darion.yaphet.spark.scala.streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

object KafkaExample {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("yarn-cluster").setAppName("StreamingExample")
    val context = new StreamingContext(conf, Seconds(3))

    //val stream = KafkaUtils.
  }
}
