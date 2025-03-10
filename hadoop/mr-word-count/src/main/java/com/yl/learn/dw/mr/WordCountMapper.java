package com.yl.learn.dw.mr;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.util.Arrays;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

    private static  final Log logger = LogFactory.getLog(WordCountMapper.class);

    public void map(LongWritable key, Text value, Context context)  {
        logger.info("key =>" + key);
        logger.info("value =>" + value);

        Arrays.stream(value.toString().split(" ")).forEach(word -> {
            try {
                context.write(new Text(word), new LongWritable(1));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
