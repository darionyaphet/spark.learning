package org.darion.yaphet.spark.scala.bagel

import org.apache.spark.bagel._
import org.apache.spark.graphx.Edge
import org.apache.spark.{SparkConf, SparkContext}

class PREdge(val targetId: String) extends Edge

class PRVertex(
                val id: String, val rank: Double, val outEdges: Seq[Edge],
                val active: Boolean) extends Vertex

class PRMessage(
                 val targetId: String, val rankShare: Double) extends Message

object Example {
  def main(args: Array[String]) {
    val conf = new SparkConf()
    val context = new SparkContext(conf)
    val input = context.textFile("resources/mllib/pagerank_data.txt")

    val numVerts = input.count()

    val verts = input.map(line => {
      val fields = line.split('\t')
      val (id, linksStr) = (fields(0), fields(1))
      val links = linksStr.split(',').map(new PREdge(_))
      (id, new PRVertex(id, 1.0 / numVerts, links, true))
    }).cache
  }
}
