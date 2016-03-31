package org.darion.yaphet.spark.streaming;

import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.*;

/**
 * 
 * @author darion.johannes.yaphet
 *
 */
public class Example {
	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setAppName("").setMaster("");
		JavaStreamingContext context = new JavaStreamingContext(conf, new Duration(1000));

	}
}
