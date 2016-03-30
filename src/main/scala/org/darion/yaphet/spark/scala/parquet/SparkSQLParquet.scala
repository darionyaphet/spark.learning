package org.darion.yaphet.spark.scala.parquet

import org.apache.spark.{SparkConf, SparkContext}

/**
  * http://mp.weixin.qq.com/s?__biz=MzIwMDI0NzYxMQ==&
  * mid=401878439&idx=1&sn=dee779198b237547301681df7782971d
  * &scene=23&srcid=0330T4wly7Bh6wxYv4BRG9tg#rd
  */
object SparkSQLParquet {

  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("SparkSQLParquet")
    val context = new SparkContext(conf)


  }
}
