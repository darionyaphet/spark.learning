package org.darion.yaphet.spark.scala.rdd

import org.apache.spark.{SparkConf, SparkContext}

object Example {
  def main(args: Array[String]) {
    val conf = new SparkConf()
      .setAppName("Resilient-Distributed-Datasets-Example")
      .setMaster("local")
    val context = new SparkContext(conf)

    //Create Resilient Distributed Datasets from HDFS & HBase
    val data = context.parallelize(1 until 4096, 4)
    println(data.count())

    //Create Resilient Distributed Datasets from HDFS & HBase
    val dfsData = context.textFile("/tmp/input")
    println(dfsData.toDebugString)


  }
}
