package org.darion.yaphet.spark.scala.mllib.frequentpatternmining

import org.apache.spark.mllib.fpm.FPGrowth
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * http://spark.apache.org/docs/1.6.2/mllib-frequent-pattern-mining.html#fp-growth
  */
class FPGrowthExample {
  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("K-means")
    val sc = new SparkContext(conf)

    val data = sc.textFile("/user/wangcg/spark.data/mllib/sample_fpgrowth.txt")

    val transactions: RDD[Array[String]] = data.map(s => s.trim.split(' '))

    val fpg = new FPGrowth()
      .setMinSupport(0.2)
      .setNumPartitions(10)
    val model = fpg.run(transactions)

    model.freqItemsets.collect().foreach { itemset =>
      println(itemset.items.mkString("[", ",", "]") + ", " + itemset.freq)
    }

    val minConfidence = 0.8
    model.generateAssociationRules(minConfidence).collect().foreach { rule =>
      println(
        rule.antecedent.mkString("[", ",", "]")
          + " => " + rule.consequent.mkString("[", ",", "]")
          + ", " + rule.confidence)
    }
  }
}
