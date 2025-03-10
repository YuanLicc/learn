package com.yl.learn.flink;

import org.apache.flink.streaming.api.datastream.AllWindowedStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;

import java.time.Duration;

public class WindowedWordCount {
    // 数据输入 ID, NAME, CNT
    // 每5S，输出一次 按照 NAME 聚合的 CNT
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(10);
        DataStreamSource<String> dss = env.socketTextStream(TestConfiguration.LOCALHOST, TestConfiguration.PORT);
        AllWindowedStream<String, TimeWindow> aws = dss.windowAll(TumblingEventTimeWindows.of(Duration.ofSeconds(10)));
    }
}
