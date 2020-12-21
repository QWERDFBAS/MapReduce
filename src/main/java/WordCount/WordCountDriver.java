package WordCount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class WordCountDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        args = new String[]{"C:\\Users\\AZZL\\Desktop\\尚硅谷\\Hadoop\\hello.txt","C:\\Users\\AZZL\\Desktop\\尚硅谷\\Hadoop\\output4"};
//        1.获取job对象
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

//        2.设置jar包存放路径
        job.setJarByClass(WordCountDriver.class);

//        3.关联Map， Reduce类
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReduce.class);

//        4.设置Mapper阶段输出数据的key， value类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

//        5.设置最终数据输出的key， value类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        job.setCombinerClass(WordCountReduce.class);

//        6.设置输入路径和输出路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

//        7.提交job
//        job.submit();
       boolean result = job.waitForCompletion(true);
       System.exit(result ? 0 : 1);
    }
}
