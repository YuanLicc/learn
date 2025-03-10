package com.yl.learn.bd.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;

public class Application {
    
    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf().setAppName("word-count");
        JavaSparkContext jsc = new JavaSparkContext(sparkConf);
        
        JavaRDD<String> rdd = jsc.textFile(args[0]);
        rdd.flatMap(line -> Arrays.asList(line.split(" ")).iterator())
                .mapToPair(word -> new Tuple2<>(word, 1))
                .reduceByKey((x, y) -> x + y)
                .mapToPair(kv -> new Tuple2<>(kv._2, kv._1))
                .sortByKey()
                .mapToPair(kv -> new Tuple2<>(kv._2, kv._1))
                .collect()
                .forEach(System.out::println);
    }
    
}
