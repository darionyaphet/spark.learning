package org.darion.yaphet.spark.scala.mllib.datatypes

import org.apache.spark.mllib.linalg.{Vector, Vectors}

/**
  * http://spark.apache.org/docs/1.6.2/mllib-data-types.html#local-vector
  */
class LocalVectorExample {
  def main(args: Array[String]): Unit = {


    // Create a dense vector (1.0, 0.0, 3.0).
    val dv: Vector = Vectors.dense(1.0, 0.0, 3.0)

    // Create a sparse vector (1.0, 0.0, 3.0) by specifying its indices and values corresponding to nonzero entries.
    val sv1: Vector = Vectors.sparse(3, Array(0, 2), Array(1.0, 3.0))

    // Create a sparse vector (1.0, 0.0, 3.0) by specifying its nonzero entries.
    val sv2: Vector = Vectors.sparse(3, Seq((0, 1.0), (2, 3.0)))
  }
}
