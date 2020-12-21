package KV;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class KVTextMap extends Mapper<Text, Text, Text, IntWritable> {

    IntWritable intWritable = new IntWritable();

    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
//        1.封装对象

//        2.写出
        context.write(key, intWritable);

    }
}
