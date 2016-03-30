package org.darion.yaphet.spark.parquet;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

/**
 * http://mp.weixin.qq.com/s?__biz=MzIwMDI0NzYxMQ==&
 * mid=401878439&idx=1&sn=dee779198b237547301681df7782971d
 * &scene=23&srcid=0330T4wly7Bh6wxYv4BRG9tg#rd
 */
public class SparkSQLParquet {
    public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local").setAppName("SparkSQLParquet");
        SQLContext context = new SQLContext(new JavaSparkContext(conf));
        DataFrame frame = context.read().parquet("resources/users.parquet");
        frame.registerTempTable("users");

        DataFrame result = context.sql("select * from users");
        JavaRDD<String> resultRDD = result.javaRDD().map(new Function<Row, String>() {
            @Override
            public String call(Row row) throws Exception {
                return "Name : " + row.getAs("name");
            }
        });

        for (String name : resultRDD.collect()) {
            System.out.println(name);
        }
    }
}
