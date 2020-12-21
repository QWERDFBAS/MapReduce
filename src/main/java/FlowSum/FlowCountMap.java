package FlowSum;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowCountMap extends Mapper<LongWritable, Text, Text, FlowBean> {

    Text k = new Text();
    FlowBean v = new FlowBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
//        1.获取一行
        String line = value.toString();

//        2.按‘\t’切割
        String[] fields = line.split("\t");

//        3.封装对象
        String phoneNum = fields[1];
        long upflow = Long.parseLong(fields[fields.length - 3]);
        long downflow = Long.parseLong(fields[fields.length - 2]);

        k.set(phoneNum);
        v.set(downflow, upflow);

//        4.写出
        context.write(k, v);
    }
}
