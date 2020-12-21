package Sort;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FlowDownSortMapper extends Mapper<LongWritable, Text, FlowBean, Text> {

    FlowBean k = new FlowBean();
    Text v = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
//    1.获取一行
        String line = value.toString();

//    2.切割
        String[] fields = line.split("\t");

//    3.封装对象
        String phonenum = fields[0];
        long upflow = Long.parseLong(fields[1]);
        long downflow = Long.parseLong(fields[2]);
        long sumflow = Long.parseLong(fields[3]);

        k.setUpFlow(upflow);
        k.setDownFlow(downflow);
        k.setSumFlow(sumflow);

        v.set(phonenum);

//    4.写出
        context.write(k, v);

    }
}
