package org.darion.yaphet.spark.scala.graphx

import org.apache.spark._
import org.apache.spark.graphx.{Edge, Graph, VertexId}
import org.apache.spark.rdd.RDD

class VertexProperty()

case class UserProperty(val name: String) extends VertexProperty

case class ProductProperty(val name: String, val price: Double) extends VertexProperty

object Example {
  def main(args: Array[String]) {
    val conf = new SparkConf().setAppName("GraphxExample").setMaster("local")
    val context = new SparkContext(conf)

    // Create an RDD for the vertices
    val users: RDD[(VertexId, (String, String))] =
      context.parallelize(Array(
        (3L, ("rxin", "student")),
        (7L, ("jgonzal", "postdoc")),
        (5L, ("franklin", "prof")),
        (2L, ("istoica", "prof"))))

    // Create an RDD for edges
    val relationships: RDD[Edge[String]] =
      context.parallelize(Array(
        Edge(3L, 7L, "collab"),
        Edge(5L, 3L, "advisor"),
        Edge(2L, 5L, "colleague"),
        Edge(5L, 7L, "pi")))

    // Define a default user in case there are relationship with missing user
    val defaultUser = ("John Doe", "Missing")

    // Build the initial Graph
    val graph = Graph(users, relationships, defaultUser)

    val verticesCount = graph.vertices.filter {
      case (id, (name, pos)) => pos == "postdoc"
    }.count()

    val edgesCount = graph.edges.filter(e => e.srcId > e.dstId).count()

    println(verticesCount)
    println(edgesCount)
  }
}
