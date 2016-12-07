package org.darion.yaphet.spark.scala.dataframe

import org.apache.spark.{SparkConf, SparkContext}

case class TutorLiveAction(episode_type: String, action: String, roomid: String, userid: String, unixtime: String)

/**
  * Created by darion.johannes.yaphet on 16/3/29.
  */
object Example {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("")
    val context = new SparkContext(conf)

    val input = context.textFile("/user/hive/warehouse/tutor.db/ods_tutor_live_action/dt=2016-09-10")
      .map(line => line.split("\t"))
      .map(token => TutorLiveAction(token(0), token(1), token(2), token(3), token(4)))

    //input.select("userid").distinct().count()
  }
}
