package org.darion.yaphet.spark.scala.mllib.clustering

import org.apache.spark.SparkConf
import org.apache.spark.mllib.clustering.StreamingKMeans
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * http://spark.apache.org/docs/1.6.2/mllib-clustering.html#streaming-k-means
  */
class StreamingKMeansExample {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("Streaming k-means")
    val ssc = new StreamingContext(conf, Seconds(1))

    val trainingData = ssc.textFileStream("/training/data/dir").map(Vectors.parse)
    val testData = ssc.textFileStream("/testing/data/dir").map(LabeledPoint.parse)

    val numDimensions = 3
    val numClusters = 2
    val model = new StreamingKMeans()
      .setK(numClusters)
      .setDecayFactor(1.0)
      .setRandomCenters(numDimensions, 0.0)

    model.trainOn(trainingData)
    model.predictOnValues(testData.map(lp => (lp.label, lp.features))).print()

    ssc.start()
    ssc.awaitTermination()
  }
}
