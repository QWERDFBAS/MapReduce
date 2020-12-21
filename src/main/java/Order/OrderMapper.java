package Order;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class OrderMapper extends Mapper<LongWritable, Text, OrderBean, NullWritable> {

    OrderBean k = new OrderBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
//        获取一行
        String line = value.toString();

//        切割
        String[] field = line.split("\t");

//        封装数据
        k.setOrder_id(Integer.parseInt(field[0]));
        k.setPrice(Double.parseDouble(field[2]));

//        写出
        context.write(k, NullWritable.get());

    }
}
