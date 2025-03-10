package com.yl.learn.spark.sql;

import org.apache.spark.sql.SparkSession;

public class HiveApplication {
    
    public static void main(String[] args) {
        SparkSession sparkSession = SparkSession.builder()
                .appName("spark-sql")
                .enableHiveSupport()
                .getOrCreate();
        
        sparkSession.sql("show databases").show();
        sparkSession.sql("show tables").show();
        
    }
    
}
