package com.yl.learn.flink;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

import java.util.Arrays;

public class WordCount {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // nc -L -p 9001
        DataStreamSource<String> ds = env.socketTextStream("localhost", 9001);
        ds.flatMap(
                (String line, Collector<Tuple2<String, Integer>> collector) -> {
                    Arrays.stream(line.split(" +")).forEach(word -> collector.collect(Tuple2.of(word, 1)));
                })
                .returns(Types.TUPLE(Types.STRING, Types.INT))
                .keyBy(tuple -> tuple.f0)
                .sum(1)
                .print();
        env.execute();
    }

}
