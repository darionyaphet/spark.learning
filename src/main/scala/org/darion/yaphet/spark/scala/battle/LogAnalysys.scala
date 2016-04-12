package org.darion.yaphet.spark.scala.battle

import org.apache.spark.{SparkConf, SparkContext}

object LogAnalysys {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("LogAnalysis")
    val context = new SparkContext(conf)

    val source = "/log/frog/year=2016/month=04/day=01/part-m-015*"
    val target = "/tmp/darion/spark/2016-04-03"
    val log = context.textFile(source)
    val timestamp = log.map(line => (if (line.split(" ").length > 1) line.split(" ")(1) else "null", 1))
    timestamp.coalesce(7).saveAsTextFile(target)
  }
}
