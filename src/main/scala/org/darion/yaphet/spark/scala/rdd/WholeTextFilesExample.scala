package org.darion.yaphet.spark.scala.rdd

import org.apache.spark.{SparkConf, SparkContext}

object WholeTextFilesExample {
  def main(args: Array[String]): Unit = {
    val context = new SparkContext(new SparkConf())
    val lines = context.wholeTextFiles("/path")
    lines.map({
      case (name, content) => (name.substring(112), content.length)
    }).collect.foreach(println)
  }
}
