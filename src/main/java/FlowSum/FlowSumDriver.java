package FlowSum;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineFileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.File;
import java.io.IOException;

public class FlowSumDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        args = new String[]{"C:\\Users\\AZZL\\Desktop\\尚硅谷\\Hadoop\\phone_data.txt","C:\\Users\\AZZL\\Desktop\\尚硅谷\\Hadoop\\output1"};

//        1.获取job对象
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

//        2.设置jar包路径
        job.setJarByClass(FlowSumDriver.class);

//        3.关联Mapper和Reduce
        job.setMapperClass(FlowCountMap.class);
        job.setReducerClass(FlowCountReduce.class);

//        4.设置Map输出格式
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);

//        5.设置最终数据的输出格式
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);


//        job.setPartitionerClass(ProvincePartitioner.class);
////        CombineFileInputFormat.setMaxInputSplitSize(job, 4194304);
//        job.setNumReduceTasks(5);

//        6.设置最终输出路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

//        7.关闭job
        boolean result = job.waitForCompletion(true);
        System.out.println(result ? 0 : 1);
    }
}
