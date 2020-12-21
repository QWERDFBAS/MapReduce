package KV;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import javax.xml.soap.Text;
import java.io.IOException;

public class KVTextReduce extends Reducer<Text, IntWritable, Text, IntWritable> {
    IntWritable value = new IntWritable();

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
//        1.累加求和
        int count = 0;
        for(IntWritable value : values) {
            count += value.get();
        }
        value.set(count);

//        2.写出
        context.write(key, value);
    }
}
