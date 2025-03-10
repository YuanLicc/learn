package com.yl.learn.flink;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.api.java.tuple.Tuple5;
import org.apache.flink.connector.kafka.source.KafkaSource;
import org.apache.flink.connector.kafka.source.enumerator.initializer.OffsetsInitializer;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumerBase;
import org.apache.flink.util.Collector;
import java.time.Duration;
import java.util.Properties;

public class KafkaJoinSum {
    public static void main(String[] args) throws Exception {

        // Set up the execution environment
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        // Set up Kafka consumer properties
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("group.id", "flink-consumer-group");

        // Create Kafka consumers for the input streams
        /*FlinkKafkaConsumerBase<String> topic1Consumer = new FlinkKafkaConsumerBase<String>(
                "topic1",
                new SimpleStringSchema(),
                properties
        );

        FlinkKafkaConsumerBase<String> topic2Consumer = new FlinkKafkaConsumerBase<>(
                "topic2",
                new SimpleStringSchema(),
                properties
        );

        // Deserialize the Kafka messages
        DataStream<Tuple4<Integer, Integer, Long, Double>> topic1Stream = env
                .fromSource(
                        topic1Consumer,
                        WatermarkStrategy.<String>forBoundedOutOfOrderness(Duration.ofSeconds(3))
                        .withTimestampAssigner((event, timestamp) -> {
                            String[] fields = event.split(",");
                            return Long.parseLong(fields[2]); // buyTime as timestamp
                        }), "topic1")
                .map(line -> {
                    String[] fields = line.split(",");
                    return Tuple4.of(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), Long.parseLong(fields[2]), Double.parseDouble(fields[3]));
                });

        DataStream<Tuple4<Integer, Integer, Long, Integer>> topic2Stream = env
                .addSource(topic2Consumer)
                .assignTimestampsAndWatermarks(WatermarkStrategy
                        .<String>forBoundedOutOfOrderness(Duration.ofSeconds(3))
                        .withTimestampAssigner((event, timestamp) -> {
                            String[] fields = event.split(",");
                            return Long.parseLong(fields[2]); // op_time as timestamp
                        }))
                .map(line -> {
                    String[] fields = line.split(",");
                    return Tuple4.of(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), Long.parseLong(fields[2]), Integer.parseInt(fields[3]));
                });

        // Connect the streams by userid and apply a tumbling window of 10 seconds
        DataStream<Tuple5<Integer, Long, Double, Integer, Integer>> joinedStream = topic1Stream
                .connect(topic2Stream)
                .keyBy(value -> value.f1) // key by userid
                .window(TumblingEventTimeWindows.of(Time.seconds(10)))
                .apply(new RichMapJoinFunction<Tuple4<Integer, Integer, Long, Double>, Tuple4<Integer, Integer, Long, Integer>, Tuple5<Integer, Long, Double, Integer, Integer>>() {

                    @Override
                    public Tuple5<Integer, Long, Double, Integer, Integer> join(Tuple4<Integer, Integer, Long, Double> first, Tuple4<Integer, Integer, Long, Integer> second) {
                        // Here we assume that there will be at most one matching event per window per key
                        // In a real scenario, you might need to handle multiple matching events using an aggregation strategy
                        Double amountSum = first != null ? first.f3 : 0.0;
                        Integer coinSum = second != null ? second.f3 : 0;

                        // Use the window end time as the timestamp for the result
                        long windowEndTime = getRuntimeContext().getCurrentWatermark(); // Note: This might not be accurate, use window meta-data if available
                        // Alternatively, you can use the end of the current window which is more precise but requires windowing context
                        // long windowEndTime = context.window().getEnd(); // This requires access to the JoinFunction.Context which is not directly available in RichMapJoinFunction

                        // Since we don't have direct access to the window end time in RichMapJoinFunction,
                        // we can use a heuristic approach by adding the window size to the latest event time seen so far.
                        // However, this is not exact and should be used with caution.
                        // For simplicity, let's just use a fixed timestamp here (not recommended for production).
                        // In a real scenario, consider using a side output stream to emit late data or a more sophisticated watermark strategy.
                        // long windowEndTime = Math.max(first != null ? first.f2 : 0, second != null ? second.f2 : 0) + 10000; // 10 seconds window

                        // Due to the limitations above, we'll print the results without a precise window end time for this example.
                        // In a real application, you should handle window end times more accurately.

                        return Tuple5.of(first.f1, System.currentTimeMillis(), amountSum, coinSum, 1); // The '1' can be used as a count of events per window, if needed.
                    }
                });

        // Note: The above code has some simplifications and assumptions.
        // For example, it assumes at most one event per key per window from each topic,
        // which might not be true in a real-world scenario.
        // Also, it uses System.currentTimeMillis() which is not ideal for event-time processing.
        // Instead, you should use the window end time or another appropriate timestamp.
        // Additionally, the handling of watermarks and late data should be more sophisticated.

        // Print the results (for demonstration purposes only)
        joinedStream.print();

        // Execute the Flink program
        env.execute("Flink Kafka Join and Aggregation Example");*/
    }
}
