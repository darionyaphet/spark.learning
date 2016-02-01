package org.darion.yaphet.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

/**
 * 
 * @author darion.johannes.yaphet
 *
 */
public class SimpleApp {

	public static void main(String[] args) {
		String log = "/tmp/README.md";
		SparkConf conf = new SparkConf().setMaster("local").setAppName("SimpleApplication");
		JavaSparkContext context = new JavaSparkContext(conf);
		JavaRDD<String> logData = context.textFile(log);

		long numAs = logData.filter(new Function<String, Boolean>() {
			private static final long serialVersionUID = -6670067550702739855L;

			public Boolean call(String line) throws Exception {
				return line.contains("a");
			}
		}).count();

		long numBs = logData.filter(new Function<String, Boolean>() {
			private static final long serialVersionUID = -8568002859714923799L;

			public Boolean call(String line) throws Exception {
				return line.contains("b");
			}
		}).count();

		System.out.println(String.format("Lines with a: %s, Lines with b: %s", numAs, numBs));
		context.close();
	}

}
