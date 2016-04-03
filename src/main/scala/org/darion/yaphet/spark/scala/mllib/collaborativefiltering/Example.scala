package org.darion.yaphet.spark.scala.mllib.collaborativefiltering

import org.apache.spark.mllib.recommendation.{ALS, Rating}
import org.apache.spark.{SparkConf, SparkContext}

object Example {
  def main(args: Array[String]) {
    val conf = new SparkConf().setMaster("local").setAppName("CollaborativeFiltering")
    val context = new SparkContext(conf)

    // Load and parse the data
    val data = context.textFile("data/mllib/als/test.data")

    val rating = data.map(
      _.split(",")
      match {
        case Array(user, item, rate) => Rating(user.toInt, item.toInt, rate.toDouble)
      })

    // Build the recommendation model using ALS
    val rank = 10
    val numIterations = 10
    val modle = ALS.train(rating, rank, numIterations, 0.01)
    println(modle)

  }
}
