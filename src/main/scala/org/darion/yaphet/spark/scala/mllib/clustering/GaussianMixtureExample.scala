package org.darion.yaphet.spark.scala.mllib.clustering

import org.apache.spark.mllib.clustering.{GaussianMixture, GaussianMixtureModel}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.{SparkConf, SparkContext}

/**
  * http://spark.apache.org/docs/1.6.2/mllib-clustering.html#gaussian-mixture
  */
class GaussianMixtureExample {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Gaussian mixture")
    val sc = new SparkContext(conf)

    // Load and parse the data
    val data = sc.textFile("data/mllib/gmm_data.txt")
    val parsedData = data.map(s => Vectors.dense(s.trim.split(' ').map(_.toDouble))).cache()

    // Cluster the data into two classes using GaussianMixture
    val gmm = new GaussianMixture().setK(2).run(parsedData)

    // Save and load model
    gmm.save(sc, "myGMMModel")
    val sameModel = GaussianMixtureModel.load(sc, "myGMMModel")

    // output parameters of max-likelihood model
    for (i <- 0 until gmm.k) {
      println("weight=%f\nmu=%s\nsigma=\n%s\n" format
        (gmm.weights(i), gmm.gaussians(i).mu, gmm.gaussians(i).sigma))
    }
  }
}
