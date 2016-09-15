package org.darion.yaphet.spark.scala.mllib.datatypes

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint

/**
  * http://spark.apache.org/docs/1.6.2/mllib-data-types.html#labeled-point
  */
class LabeledPointExample {
  def main(args: Array[String]): Unit = {
    // Create a labeled point with a positive label and a dense feature vector.
    val pos = LabeledPoint(1.0, Vectors.dense(1.0, 0.0, 3.0))

    // Create a labeled point with a negative label and a sparse feature vector.
    val neg = LabeledPoint(0.0, Vectors.sparse(3, Array(0, 2), Array(1.0, 3.0)))
  }
}
