package com.yl.learn.dw.mr;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordCountReducer extends Reducer<Text, LongWritable, Text, LongWritable> {

    private static final Log logger = LogFactory.getLog(WordCountReducer.class);

    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {

        LongWritable longWritable = new LongWritable(0);

        values.forEach(value -> {
            longWritable.set(longWritable.get() + value.get());
        });

        context.write(key, longWritable);
    }
}
