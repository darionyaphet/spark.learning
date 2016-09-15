package org.darion.yaphet.spark.scala.mllib.clustering

import org.apache.spark.mllib.clustering.{PowerIterationClustering, PowerIterationClusteringModel}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * http://spark.apache.org/docs/1.6.2/mllib-clustering.html#power-iteration-clustering-pic
  */
class PowerIterationClusteringExample {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Power iteration clustering")
    val sc = new SparkContext(conf)

    // Load and parse the data
    val data = sc.textFile("data/mllib/pic_data.txt")
    val similarities = data.map { line =>
      val parts = line.split(' ')
      (parts(0).toLong, parts(1).toLong, parts(2).toDouble)
    }

    // Cluster the data into two classes using PowerIterationClustering
    val pic = new PowerIterationClustering()
      .setK(2)
      .setMaxIterations(10)
    val model = pic.run(similarities)

    model.assignments.foreach { a =>
      println(s"${a.id} -> ${a.cluster}")
    }

    // Save and load model
    model.save(sc, "myModelPath")
    val sameModel = PowerIterationClusteringModel.load(sc, "myModelPath")
  }
}
