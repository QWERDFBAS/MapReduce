package Table;

import com.google.common.collect.Table;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

public class TableMapper extends Mapper<LongWritable, Text, Text, TableBean> {

    String name;
    TableBean tablebean = new TableBean();
    Text k = new Text();

    @Override
    protected void setup(Mapper<LongWritable, Text, Text, TableBean>.Context context) throws IOException, InterruptedException {

        FileSplit fileSplit = (FileSplit) context.getInputSplit();

        name = fileSplit.getPath().getName();
    }

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();

        if(name.startsWith("order")) {
            String[] fields = line.split("\t");

            tablebean.setId(fields[0]);
            tablebean.setPid(fields[1]);
            tablebean.setAmount(Integer.parseInt(fields[2]));
            tablebean.setPname("");
            tablebean.setFlag("order");

            k.set(fields[1]);

        } else {
            String[] fields = line.split("\t");

            tablebean.setId("");
            tablebean.setPid(fields[0]);
            tablebean.setAmount(0);
            tablebean.setPname(fields[1]);
            tablebean.setFlag("pd");

            k.set(fields[0]);
        }

        context.write(k, tablebean);
    }
}
