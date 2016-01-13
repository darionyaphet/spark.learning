package org.darion.yaphet.spark.scala

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object SimpleApp {
  def main(args: Array[String]): Unit = {
    val log = "/tmp/README.md"
    val conf = new SparkConf().setMaster("local").setAppName("SimpleApplication")
    val context = new SparkContext(conf)
    val logData = context.textFile(log, 2).cache()
    val numAs = logData.filter { line => line.contains("a") }.count()
    val numBs = logData.filter { line => line.contains("b") }.count()
    println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
  }
} 