package FindFriends;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class FindFriendsOneMapper extends Mapper<LongWritable, Text, Text, Text> {

    Text k = new Text();
    Text v = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();

        String[] fields = line.split(":");

        String person = fields[0];
        String[] friends = fields[1].split(",");

        for (String friend : friends) {
            k.set(friend);
            v.set(person);
            context.write(k, v);
        }
    }
}
