package org.darion.yaphet.spark.scala.HBase

import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.ConnectionFactory
import org.apache.hadoop.hbase.mapreduce.TableInputFormat
import org.apache.spark.{SparkConf, SparkContext}

object Example {
  def main(args: Array[String]) {

    //
    val tableConf = HBaseConfiguration.create()
    tableConf.set("hbase.zookeeper.property.clientPort", "2181")
    tableConf.set("hbase.zookeeper.quorum", "master")
    tableConf.set(TableInputFormat.INPUT_TABLE, "user")

    val connection = ConnectionFactory.createConnection()


    val conf = new SparkConf().setMaster("local").setAppName("LogAnalysis")
    val context = new SparkContext(conf)



    // Load data from HBase

    // Write data to HBase
  }
}
