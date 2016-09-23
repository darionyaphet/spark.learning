package org.darion.yaphet.spark.scala.dataframe

import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}

object NginxLog {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("")
    val sc = new SparkContext(conf)

    val context = new HiveContext(sc)
    val frame = context.sql("SELECT * FROM `nginx` WHERE year = 2016 AND month = 09 AND day = 01")

    frame.select("ip", "time", "method", "request", "protocol", "status", "refer", "size", "agent", "ratio", "host", "year", "month", "day")
      .coalesce(16)
      .write
      .save("/tmp/nginx.parquet")
  }
}
