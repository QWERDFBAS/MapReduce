package KV;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueLineRecordReader;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.beans.PropertyEditorSupport;
import java.io.IOException;

public class KVTextDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        args = new String[]{"", ""};

        Configuration conf = new Configuration();
        conf.set(KeyValueLineRecordReader.KEY_VALUE_SEPERATOR, " ");

//        1.获取job对象
        Job job = Job.getInstance(conf);

//        2.设置jar存储路径
        job.setJarByClass(KVTextDriver.class);

//        3.关联Map， Reducer
        job.setMapperClass(KVTextMap.class);
        job.setReducerClass(KVTextReduce.class);

//        4.设置Map输出格式
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

//        5.设置最终输出格式
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);


        job.setInputFormatClass(KeyValueTextInputFormat.class);
//        6.设置最终输出路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

//        7.关闭job
        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);
    }
}
