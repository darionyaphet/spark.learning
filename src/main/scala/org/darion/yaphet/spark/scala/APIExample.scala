package org.darion.yaphet.spark.scala

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import java.util.Arrays

object APIExample {

  def myfunc(index: Int, iter: Iterator[(String)]): Iterator[String] = {
    iter.toList.map(x => "[partID:" + index + ", val: " + x + "]").iterator
  }

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("API Example")
    val context = new SparkContext(conf)

    val numbers = context.parallelize(List(1, 2, 3, 4, 5, 6), 2)

    val letters = context.parallelize(List("a", "b", "c", "d", "e", "f"), 2)
    print(letters.mapPartitionsWithIndex(myfunc).collect.toString())

  }
}